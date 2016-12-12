package impl

import api.Targets

import java.util.stream.Collectors

/**
 * Created by eoin on 11/12/2016.
 */
class CommandExecutor {

    Map<Integer, Output> outputs
    Map<Integer, Bot> bots

    CommandExecutor() {
        this.outputs = new HashMap<>()
        this.bots = new HashMap<>()
    }

    void instruct(Object instruction) {
        if (instruction instanceof InputInstruction) {
            addInstruction(instruction as InputInstruction)
        } else if (instruction instanceof  Instruction) {
            addInstruction(instruction as Instruction)
        }
    }

    void addInstruction (InputInstruction inputInstruction) {
        Integer sourceId = inputInstruction.getTargetId()
        Bot sourceBot = bots.get(sourceId)
        if (sourceBot == null) {
            sourceBot = new Bot(sourceId)
            bots.put(sourceId, sourceBot)
        }
        sourceBot.addChip(new Chip(inputInstruction.getTargetChipId()))
    }

    void addInstruction(Instruction instruction) {
        Integer sourceId = instruction.getSource()
        Bot sourceBot = bots.get(sourceId)
        if (sourceBot == null) {
            sourceBot = new Bot(sourceId)
            bots.put(sourceId, sourceBot)
        }
        sourceBot.addInstruction(instruction)
    }

    void execute(Instruction instruction) {
        Integer source = instruction.getSource()
        Bot bot = bots.get(source)
        for(Target target : instruction.getTargets()) {
            Chip removedChip = bot.removeChip(target.getChip())
            Integer targetId = target.getTargetId()
            switch (target.getTargets()) {
                case Targets.BOT:
                    Bot targetBot = bots.get(targetId)
                    if (targetBot == null) {
                        targetBot = new Bot(targetId)
                        bots.put(targetId, targetBot)
                    }
                    targetBot.addChip(removedChip)
                    break
                case Targets.OUTPUT:
                    Output targetOutput = outputs.get(targetId)
                    if (targetOutput == null) {
                        targetOutput = new Output(targetId)
                        outputs.put(targetId, targetOutput)
                    }
                    targetOutput.addChip(removedChip)
                    break
                default:
                    throw new UnsupportedOperationException()
            }
        }
    }

    Bot getBotComparing(int low, int high) {
        Comparison comparison = new Comparison(Integer.valueOf(high), Integer.valueOf(low))
        this.bots.values().stream().filter({
            b -> b.getComparisonsPerformedByBot().stream().anyMatch({
                c -> c.equals(comparison)
            })
        }).collect(Collectors.toList()).get(0)
    }
}

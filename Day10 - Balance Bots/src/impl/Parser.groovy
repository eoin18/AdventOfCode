package impl

import api.Targets

/**
 * Created by eoin on 11/12/2016.
 */
class Parser {

    CommandExecutor executor

    Parser(CommandExecutor executor){
        this.executor = executor
    }


    Object parse (String line) {
        if (line.startsWith("value")) {
            String[] split = line.split()
            InputInstruction inputInstruction = new InputInstruction(Integer.parseInt(split[5]), Integer.parseInt(split[1]))
            return inputInstruction
        } else if (line.startsWith("bot")){
            String[] split = line.split()
            Instruction instruction = new Instruction(executor, Integer.parseInt(split[1]))
            instruction.addLowTarget(new Target(split[5] == "bot" ? Targets.BOT : Targets.OUTPUT, Integer.parseInt(split[6])))
            instruction.addHighTarget(new Target(split[10] == "bot" ? Targets.BOT : Targets.OUTPUT, Integer.parseInt(split[11])))
            return instruction
        }

        throw new UnsupportedOperationException()
    }

}

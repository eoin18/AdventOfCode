package impl

/**
 * Created by eoin on 11/12/2016.
 */
class Instruction {

    CommandExecutor commandExecutor
    List<Target> targets
    Target highTarget
    Target lowTarget
    Integer source

    Instruction(CommandExecutor executor, Integer source) {
        this.commandExecutor = executor
        this.source = source
        this.targets = new ArrayList<>()
    }

    void execute(){
        System.println("executing " + this.highTarget.toString() + " and " + this.lowTarget.toString())
        this.commandExecutor.execute(this)
    }

    Integer getSource() {
        return this.source
    }

    void addHighTarget(Target target) {
        this.highTarget = target
        this.targets.add(target)
    }

    void addLowTarget(Target target) {
        this.lowTarget = target
        this.targets.add(target)
    }

    Target getHighTarget(){
        this.highTarget
    }

    Target getLowTarget() {
        this.lowTarget
    }

    List<Target> getTargets() {
        this.targets
    }



}

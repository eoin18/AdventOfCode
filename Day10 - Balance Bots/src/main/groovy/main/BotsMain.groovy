package main

import impl.Bot
import impl.CommandExecutor
import impl.Output
import impl.Parser

/**
 * Created by eoin on 11/12/2016.
 */
class BotsMain {

    static String inputFile = "D:\\Git\\AdventOfCode\\Day10 - Balance Bots\\resources\\input.txt"

     static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor()
        Parser parser = new Parser(executor)
        File inputFile = new File(inputFile)
        inputFile.eachLine {
            line ->
                executor.instruct(parser.parse(line))
        }

         Bot result = executor.getBotComparing(17, 61)

         System.out.println(result.getValue())

         Collection<Output> outputs = executor.getOutputs().values()

         for (Output output : outputs) {
             System.out.println(output)
         }

    }
}

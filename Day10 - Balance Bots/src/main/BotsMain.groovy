package main

import impl.Bot
import impl.CommandExecutor
import impl.Parser

/**
 * Created by eoin on 11/12/2016.
 */
class BotsMain {

    static String inputFile = "/Users/eoin/Documents/AdventOfCode/Day10 - Balance Bots/resources/input.txt"

     static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor()
        Parser parser = new Parser(executor)
        File inputFile = new File(inputFile)
        inputFile.eachLine {
            line ->
                executor.instruct(parser.parse(line))
        }

         Bot result = executor.getBotComparing(5, 2)

         System.out.println(result.getValue())

    }
}

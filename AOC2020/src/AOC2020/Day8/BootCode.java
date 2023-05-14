package AOC2020.Day8;

import java.util.ArrayList;
import java.util.HashSet;

public class BootCode {
    ArrayList<Instruction> instructionList;
    HashSet<Integer> visitedInstructions;
    int accumulator;
    int pointer;
    boolean didFinish = false;

    public BootCode(ArrayList<String> instructionList) {
        accumulator = 0;
        pointer = 0;
        visitedInstructions = new HashSet<>();
        this.instructionList = new ArrayList<>();
        for(String instruction : instructionList) {
            instruction = instruction.trim();
            String[] tokens = instruction.split(" ");
            Instruction newInstruction = new Instruction(tokens[0],Integer.parseInt(tokens[1]));
            this.instructionList.add(newInstruction);
        }
    }

    public int runCode() {
        while(!visitedInstructions.contains(pointer) && pointer < instructionList.size()) {
            Instruction instruction = instructionList.get(pointer);
            visitedInstructions.add(pointer);
            executeInstruction(instruction);
        }
        if(pointer >= instructionList.size())
            didFinish = true;
        visitedInstructions.clear();
        return accumulator;
    }

    private void executeInstruction(Instruction instruction) {
        String name = instruction.name;
        int arg = instruction.argument;
        switch (name) {
            case "acc":
                accumulator += arg;
                pointer++;
                break;
            case "jmp":
                pointer += arg;
                break;
            default:
                pointer++;
        }
    }

    public int runCode2() {
        String lastInstructionName;
        for(int i = 0; i < instructionList.size(); i++) {
            Instruction instruction = instructionList.get(i);
            if(!instruction.name.equals("acc") && instruction.argument != 0) {
                if (instruction.name.equals("nop")) {
                    lastInstructionName = "nop";
                    instruction.name = "jmp";
                }
                else {
                    lastInstructionName = "jmp";
                    instruction.name = "nop";
                }
                int accumulator = runCode();
                if (didFinish)
                    return accumulator;
                this.accumulator = 0;
                this.pointer = 0;
                instruction.name = lastInstructionName;
            }
        }
        return 0;
    }
}

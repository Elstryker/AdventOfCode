import * as help from "../Common/help.js";

function processInput(input: string) {
  // Split 2 parts of the input: 0 - state, 1 - instructions
  const splitInput = input.split("\n\n");

  // Split boxes from numbers
  const splitState: string[] = splitInput[0].split("\n");
  const boxLines: string[] = splitState.splice(0, splitState.length - 1);

  // Process each line of boxes
  const elementBoxLines: string[][] = boxLines.map((line) => {
    const newLine: string[] = [];
    for (let i = 1; i < line.length; i += 4) {
      newLine.push(line[i]);
    }
    return newLine;
  });

  // Create stacks for each box
  const stacks: string[][] = Array.from(
    { length: elementBoxLines[0].length },
    () => []
  );

  // Add each box to the corresponding stack
  elementBoxLines.forEach((line: string[]) => {
    line.forEach((char, index) => {
      if (char !== " ") {
        stacks[index].unshift(char);
      }
    });
  });

  // Convert string instructions to object instructions
  const instructions: string[] = splitInput[1].split("\n");
  const getNums = /\d+/g;
  const instructionsData = instructions.map((inst) => {
    const nums = inst.match(getNums)?.map(Number) || [];
    return {
      count: nums[0],
      from: nums[1],
      to: nums[2],
    };
  });

  // Return an object with the 2 parts of the input processed
  return {
    state: stacks,
    instructions: instructionsData,
  };
}

function executeInstructions(
  input: {
    state: string[][];
    instructions: { count: number; from: number; to: number }[];
  },
  reverse = true
) {
  const state = input.state;
  const instructions = input.instructions;

  instructions.forEach((ins) => {
    // Indices start with 0 while the problem stacks identifiers start at 1
    const indexFrom = ins.from - 1;
    const indexTo = ins.to - 1;
    const numberMovingBoxes = ins.count;
    let movingBoxes = state[indexFrom].splice(
      state[indexFrom].length - numberMovingBoxes,
      numberMovingBoxes
    );
    if (reverse) {
      movingBoxes = movingBoxes.reverse();
    }
    state[indexTo].push(...movingBoxes);
  });

  return state;
}

function getLastBoxOfEachStack(finalState: string[][]) {
  const lastBoxes: string[] = [];

  finalState.forEach((stack) => {
    lastBoxes.push(stack.at(-1) || " ");
  });

  return lastBoxes.join("");
}

export function Part1() {
  // const inp = help.readShortInputByDay(5);
  const inp = help.readInputByDay(5);

  const processedInput = processInput(inp);
  const finalState = executeInstructions(processedInput);
  const solution = getLastBoxOfEachStack(finalState);

  console.log("Part 1:", solution);
}

export function Part2() {
  // const inp = help.readShortInputByDay(5);
  const inp = help.readInputByDay(5);

  const processedInput = processInput(inp);
  const finalState = executeInstructions(processedInput, false);
  const solution = getLastBoxOfEachStack(finalState);

  console.log("Part 2:", solution);
}

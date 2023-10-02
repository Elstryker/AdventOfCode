import * as help from "../Common/help.js";

function processInstruction(inpLine: string) {
  const tokens = inpLine.split(" ");
  if (tokens[0] === "addx") {
    return [2, Number(tokens[1])];
  } else {
    return [1, 0];
  }
}

function getSignalValues(instructions: number[][]) {
  let cycle = 1;
  let register = 1;
  const checkpoints = [20, 60, 100, 140, 180, 220];
  const signalValues: number[] = [];

  let instructionNum = 0;
  while (instructionNum < instructions.length) {
    const instruction = instructions[instructionNum];
    instruction[0]--;
    if (instruction[0] === 0) {
      register += instruction[1];
      instructionNum++;
    }

    cycle++;

    let checkpointIndex = checkpoints.indexOf(cycle);
    if (checkpointIndex !== -1) {
      signalValues.push(register * checkpoints[checkpointIndex]);
    }
  }

  return signalValues;
}

function getScreenFrameFromInstructions(instructions: number[][]) {
  let register = 1;
  let instructionNum = 0;
  const frameInfo: String[][] = [];

  for (let row = 0; row < 6; row++) {
    frameInfo.push([]);

    for (let col = 0; col < 40; col++) {
      let spriteValues = [register - 1, register, register + 1];
      let charToPrint = ".";

      if (spriteValues.includes(col)) {
        charToPrint = "#";
      }

      frameInfo[row].push(charToPrint);

      const instruction = instructions[instructionNum];
      instruction[0]--;

      if (instruction[0] === 0) {
        register += instruction[1];
        instructionNum++;
      }
    }
  }

  return frameInfo;
}

export function Part1() {
  // const inp = help.readShortInputByDay(10);
  const inp = help.readInputByDay(10);
  const instructions = inp.split("\n").map((line) => processInstruction(line));
  const signalValues = getSignalValues(instructions);
  const result = signalValues.reduce((acc, ele) => acc + ele, 0);
  console.log("Part 1:", result);
}

export function Part2() {
  // const inp = help.readShortInputByDay(10);
  const inp = help.readInputByDay(10);
  const instructions = inp.split("\n").map((line) => processInstruction(line));
  const screen = getScreenFrameFromInstructions(instructions);
  console.log("Part 2:");
  screen.forEach((line) => {
    console.log(line.join(""));
  });
}

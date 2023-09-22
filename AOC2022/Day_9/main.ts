import * as help from "../Common/help.js";

class Coord {
  x: number;
  y: number;

  constructor(xOrCoord?: number | Coord, y?: number) {
    if (xOrCoord instanceof Coord) {
      this.x = xOrCoord.x;
      this.y = xOrCoord.y;
    } else {
      this.x = xOrCoord ?? 0;
      this.y = y ?? 0;
    }
  }

  isAdjacentOrOverlap(coord: Coord) {
    const xDifference = Math.abs(this.x - coord.x);
    const yDifference = Math.abs(this.y - coord.y);

    if (xDifference > 1 || yDifference > 1) {
      return false;
    }

    return true;
  }

  equals(coord: Coord) {
    if (this.x === coord.x && this.y === coord.y) {
      return true;
    }

    return false;
  }
}

function processHeadDirection(direction: string) {
  const processed = {
    coord: "",
    vector: 0,
  };

  switch (direction) {
    case "R":
      processed.coord = "x";
      processed.vector = 1;
      break;
    case "L":
      processed.coord = "x";
      processed.vector = -1;
      break;
    case "U":
      processed.coord = "y";
      processed.vector = 1;
      break;
    case "D":
      processed.coord = "y";
      processed.vector = -1;
      break;
  }

  return processed;
}

function addCoordToSet(coord: Coord, set: Coord[]) {
  if (set.some((ele) => ele.equals(coord))) {
    return;
  }

  set.push(new Coord(coord));
}

function getTailPositionsFromInstructions(inpLines: string[]): Coord[] {
  const tailPositions: Coord[] = [new Coord()];
  const headPosition: Coord = new Coord();
  let tailPosition: Coord = new Coord();
  let lastHeadPosition: Coord = new Coord();

  inpLines.forEach((line) => {
    const insData = line.split(" ");
    const direction = insData[0];
    const steps = Number(insData[1]);
    const processedDirection = processHeadDirection(direction);

    for (let i = 0; i < steps; i++) {
      headPosition[processedDirection.coord] += processedDirection.vector;
      if (!tailPosition.isAdjacentOrOverlap(headPosition)) {
        tailPosition = new Coord(lastHeadPosition);
        addCoordToSet(tailPosition, tailPositions);
      }

      lastHeadPosition = new Coord(headPosition);
    }
  });

  return tailPositions;
}

function processKnotDirection(currentKnot: Coord, forwardKnot: Coord) {
  let x = 0;
  let y = 0;

  if (forwardKnot.x > currentKnot.x) {
    x = 1;
  } else if (forwardKnot.x < currentKnot.x) {
    x = -1;
  }

  if (forwardKnot.y > currentKnot.y) {
    y = 1;
  } else if (forwardKnot.y < currentKnot.y) {
    y = -1;
  }

  return {
    x,
    y,
  };
}

function getTailPositionsFromInstructions2(inpLines: string[]): Coord[] {
  const tailPositions: Coord[] = [new Coord()];
  const knotPositions: Coord[] = Array.from({ length: 10 }, () => new Coord());

  inpLines.forEach((line) => {
    const insData = line.split(" ");
    const direction = insData[0];
    const steps = Number(insData[1]);
    const processedDirection = processHeadDirection(direction);

    for (let i = 0; i < steps; i++) {
      const headPosition = knotPositions[0];
      headPosition[processedDirection.coord] += processedDirection.vector;
      let stop = false;

      for (let knot = 1; knot < 10 && !stop; knot++) {
        if (!knotPositions[knot].isAdjacentOrOverlap(knotPositions[knot - 1])) {
          const moves = processKnotDirection(
            knotPositions[knot],
            knotPositions[knot - 1]
          );
          for (const coord in moves) {
            knotPositions[knot][coord] += moves[coord];
          }

          if (knot === 9) {
            addCoordToSet(knotPositions[knot], tailPositions);
          }
        } else {
          stop = true;
        }
      }

    }
    // printScreen(-11, 14, -5, 15, knotPositions);
    // console.log('');
  });

  return tailPositions;
}

export function Part1() {
  // const inp = help.readShortInputByDay(9);
  const inp = help.readInputByDay(9);
  const inpLines = inp.split("\n");
  const tailPositions: Coord[] = getTailPositionsFromInstructions(inpLines);
  console.log("Part 1:", tailPositions.length);
}

export function Part2() {
  // const inp = help.readShortInputByDay(9);
  const inp = help.readInputByDay(9);
  const inpLines = inp.split("\n");
  const tailPositions: Coord[] = getTailPositionsFromInstructions2(inpLines);
  console.log("Part 2:", tailPositions.length);
}

function printScreen(
  minX: number,
  maxX: number,
  minY: number,
  maxY: number,
  knotPositions: Coord[]
) {
  for (let y = maxY; y >= minY; y--) {
    for (let x = minX; x <= maxX; x++) {
      let index = knotPositions.findIndex(c => c.equals(new Coord(x, y)))
      if(index === -1) {
        process.stdout.write(".");
      }
      else {
        process.stdout.write(String(index));
      }
    }
    console.log('');
  }
}

import * as help from "../Common/help.js";

interface Coord {
  col: number;
  row: number;
}

function addCoordToSet(coord: Coord, set: Coord[]) {
  if (set.some((ele) => ele.col === coord.col && ele.row === coord.row)) {
    return;
  }

  set.push(coord);
}

function countVisibleTrees(heightMatrix: number[][]) {
  const lastRow = heightMatrix.length - 1;
  const lastColumn = heightMatrix.length - 1;
  const visibleTrees: Coord[] = [];

  const parameters = {
    col: [
      [0, 1],
      [lastColumn, -1],
    ],
    row: [
      [0, 1],
      [lastRow, -1],
    ],
  };
  let lastHeight = -1;
  // Check Horizontal
  for (let row = 0; row <= lastRow; row++) {
    parameters.col.forEach(([startValue, increment]) => {
      lastHeight = -1;
      for (
        let col = startValue;
        col <= lastColumn && col >= 0;
        col += increment
      ) {
        const selectedHeight = heightMatrix[row][col];

        if (selectedHeight > lastHeight) {
          addCoordToSet({ col, row }, visibleTrees);
          lastHeight = selectedHeight;
        }
      }
    });
  }
  // Check Vertical
  for (let col = 0; col <= lastColumn; col++) {
    parameters.row.forEach(([startValue, increment]) => {
      lastHeight = -1;
      for (let row = startValue; row <= lastRow && row >= 0; row += increment) {
        const selectedHeight = heightMatrix[row][col];

        if (selectedHeight > lastHeight) {
          addCoordToSet({ col, row }, visibleTrees);
          lastHeight = selectedHeight;
        }
      }
    });
  }

  return visibleTrees.length;
}

function calculateHighestScenicScore(heightMatrix: number[][]) {
  const lastRow = heightMatrix.length - 1;
  const lastColumn = heightMatrix.length - 1;
  let highestScenicScore = 0;

  for (let row = 0; row <= lastRow; row++) {
    for (let col = 0; col <= lastColumn; col++) {
      // Height of tree
      const currentHeight = heightMatrix[row][col];
      // Score of tree
      let currentScore = 1;

      // Search in every direction
      for (let horizontalInc = -1; horizontalInc <= 1; horizontalInc++) {
        for (let verticalInc = -1; verticalInc <= 1; verticalInc++) {
          // We just want vertical and horizontal directions
          if (Math.abs(horizontalInc + verticalInc) !== 1) {
            continue;
          }

          // Distance var
          let distance = 0;
          let searchHeight = -1;

          for (
            let searchCol = col + verticalInc, searchRow = row + horizontalInc;
            searchCol >= 0 &&
            searchCol <= lastColumn &&
            searchRow >= 0 &&
            searchRow <= lastRow &&
            searchHeight < currentHeight;
            searchRow += horizontalInc, searchCol += verticalInc
          ) {
            distance++;
            searchHeight = heightMatrix[searchRow][searchCol];
          }

          currentScore *= distance;
        }
      }

      if (currentScore > highestScenicScore) {
        highestScenicScore = currentScore;
      }

      currentScore = 0;
    }
  }

  return highestScenicScore;
}

function createHeightMatrix(inp: string) {
  const inpLines = inp.split("\n");
  const heightMatrix: number[][] = [];

  inpLines.forEach((line) => {
    const heights = line.split("").map(Number);
    const lineHeights: number[] = [];

    heights.forEach((num) => {
      lineHeights.push(num);
    });

    heightMatrix.push(lineHeights);
  });

  return heightMatrix;
}

export function Part1() {
  // const inp = help.readShortInputByDay(8);
  const inp = help.readInputByDay(8);
  const heightMatrix = createHeightMatrix(inp);
  const result = countVisibleTrees(heightMatrix);
  console.log("Part 1:", result);
}

export function Part2() {
  // const inp = help.readShortInputByDay(8);
  const inp = help.readInputByDay(8);
  const heightMatrix = createHeightMatrix(inp);
  const result = calculateHighestScenicScore(heightMatrix);
  console.log("Part 2:", result);
}

import * as help from "../Common/help.js";

import { Grid } from "./grid";
import { PathsFinder } from "./pathsFinder";
import { START_NODE_NAME } from "./node";

export function Part1() {
  // const inp = help.readShortInputByDay(12);
  const inp = help.readInputByDay(12);

  const inpLines = inp.split("\n");
  const grid: Grid = new Grid().build(inpLines);
  const pathFinder = new PathsFinder(grid);
  const res = pathFinder.getLowestPathsSteps(
    grid
      .getNodes()
      .find((n) => n.getName() === START_NODE_NAME)!
      .getPosition()
  );

  console.log("Part 1:", res);
}

export function Part2() {
  // const inp = help.readShortInputByDay(12);
  const inp = help.readInputByDay(12);
  const inpLines = inp.split("\n");
  const grid: Grid = new Grid().build(inpLines);
  const pathFinder = new PathsFinder(grid);

  const res = grid
    .getNodes()
    .filter((n) => n.getLevel() === 1)
    .map((node) => node.getPosition())
    .reduce((acc, startingPosition) => {
      const lowestPathSteps = pathFinder.getLowestPathsSteps(startingPosition);

      if (acc > lowestPathSteps) {
        return lowestPathSteps;
      }

      return acc;
    }, Infinity);

  console.log("Part 2:", res);
}

// Credit: AdEnvironmental715 Reddit
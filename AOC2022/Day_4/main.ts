import * as help from "../Common/help.js";

function calculateOverlapping(pair: any[]) {
  if (pair[0].min > pair[1].max || pair[0].max < pair[1].min) {
    return false;
  }

  return true;
}

function calculateContainment(pair: any[]) {
  if (
    (pair[0].min <= pair[1].min && pair[0].max >= pair[1].max) || // first contains second
    (pair[1].min <= pair[0].min && pair[1].max >= pair[0].max) // second contains first
  ) {
    return true;
  }

  return false;
}

function parseStringPair(stringPair: string) {
  const pair = stringPair.split(",");
  const parsedPair = pair.map((range: string) => {
    const rangePair = range.split("-").map((num) => parseInt(num));
    const min = Math.min(rangePair[0], rangePair[1]);
    const max = Math.max(rangePair[0], rangePair[1]);
    return {
      min,
      max,
    };
  });

  return parsedPair;
}

export function Part1() {
  // const inp = help.readShortInputByDay(4);
  const inp = help.readInputByDay(4);

  const groups: string[] = inp.split("\n");

  const result = groups
    .map((group) => parseStringPair(group))
    .reduce((acc, group) => {
      if (calculateContainment(group)) {
        return acc + 1;
      }

      return acc;
    }, 0);

  console.log("Part 1:", result);
}

export function Part2() {
  // const inp = help.readShortInputByDay(4);
  const inp = help.readInputByDay(4);

  const groups: string[] = inp.split("\n");

  const result = groups
    .map((group) => parseStringPair(group))
    .reduce((acc, group) => {
      if (calculateOverlapping(group)) {
        return acc + 1;
      }

      return acc;
    }, 0);

  console.log("Part 2:", result);
}

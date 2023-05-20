import { log } from "console";
import * as help from "../Common/help.js";

export function Day1Part1() {
  // let inp = help.readShortInputByDay(1)
  let inp = help.readInputByDay(1);

  let elvesCalories = inp.split("\n\n");

  elvesCalories = elvesCalories.map(groupElfCalories);

  let mostCalories = Math.max(...elvesCalories);

  log("Part 1", mostCalories);
}

export function Day1Part2() {
  // let inp = help.readShortInputByDay(1);
  let inp = help.readInputByDay(1);

  let elvesCalories = inp.split("\n\n");

  elvesCalories = elvesCalories.map(groupElfCalories);

  elvesCalories = elvesCalories.sort((a, b) => a - b).slice(-3)
  
  log("Part 2", elvesCalories.reduce((a, b) => a + b));
}

let groupElfCalories = (caloriesString) => {
  let allCalories = caloriesString.split("\n");
  let allCaloriesNums = allCalories.map((x) => Number.parseInt(x));
  let summedCalories = allCaloriesNums.reduce((acc, current) => acc + current);
  return summedCalories;
};

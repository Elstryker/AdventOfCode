import { log } from 'console';
import * as help from '../Common/help.js';

const groupElfCalories = (caloriesString) => {
  const allCalories = caloriesString.split('\n');
  const allCaloriesNums = allCalories.map((x) => Number.parseInt(x, 10));
  const summedCalories = allCaloriesNums.reduce((acc, current) => acc + current);
  return summedCalories;
};

export function Day1Part1() {
  // let inp = help.readShortInputByDay(1)
  const inp = help.readInputByDay(1);

  let elvesCalories = inp.split('\n\n');

  elvesCalories = elvesCalories.map(groupElfCalories);

  const mostCalories = Math.max(...elvesCalories);

  log('Part 1', mostCalories);
}

export function Day1Part2() {
  // let inp = help.readShortInputByDay(1);
  const inp = help.readInputByDay(1);

  let elvesCalories = inp.split('\n\n');

  elvesCalories = elvesCalories.map(groupElfCalories);

  elvesCalories = elvesCalories.sort((a, b) => a - b).slice(-3);

  log('Part 2', elvesCalories.reduce((a, b) => a + b));
}

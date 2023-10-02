export default class Monkey {
  items: number[];
  operation: Function;
  test: number;
  testTrue: number;
  testFalse: number;

  constructor(inputString: string) {
    const itemsReg = /Starting Items: *(.+)/i;
    const operationReg = /Operation: *new *= *(.+)/i;
    const testReg = /Test: divisible by (\d+)/i;
    const testTrueReg = /If true: throw to monkey (\d+)/i;
    const testFalseReg = /If false: throw to monkey (\d+)/i;

    this.items = itemsReg.exec(inputString)![1].split(/, */).map(Number);
    this.operation = new Function(
      "old",
      "return " + operationReg.exec(inputString)![1]
    );
    this.test = Number(testReg.exec(inputString)![1]);
    this.testTrue = Number(testTrueReg.exec(inputString)![1]);
    this.testFalse = Number(testFalseReg.exec(inputString)![1]);
  }

  testPass(value: number) {
    if (value % this.test === 0) {
      return true;
    }

    return false;
  }
}

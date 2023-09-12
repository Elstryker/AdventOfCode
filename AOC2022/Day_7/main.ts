import * as help from "../Common/help.js";

enum Type {
  file,
  directory,
}

class Node {
  name!: string;
  type!: Type;
  size!: number;
  children?: Map<string, Node>;
  parent?: Node;

  constructor(
    name: string,
    type: Type,
    size: number,
    parent?: Node,
    children?: Map<string, Node>
  ) {
    this.name = name;
    this.type = type;
    this.size = size;
    this.parent = parent;

    if (type === Type.directory) {
      if (children) {
        this.children = children;
      } else {
        this.children = new Map<string, Node>();
      }
    } else {
      children = undefined;
    }
  }
}

function updateParentsSize(node: Node, size: number): void {
  for (
    let pointer = node.parent;
    pointer !== undefined;
    pointer = pointer.parent
  ) {
    pointer.size += size;
  }
}

function sumAllDirSizesBelow(dirTree: Node, maxSize: number): number {
  let size = 0;

  if (dirTree.type === Type.directory && dirTree.children) {
    for (const [_, value] of dirTree.children) {
      size += sumAllDirSizesBelow(value, maxSize);
    }

    if (dirTree.size <= 100000) {
      size += dirTree.size;
    }
  }

  return size;
}

function findSmallestDirToRemove(
  dirTree: Node,
  requiredSpace: number,
  unusedSpace: number
): Node | undefined {
  const nodeSize = dirTree.size;
  const unusedSpaceIfDeleted = unusedSpace + nodeSize;
  let result: Node | undefined = undefined;

  if (dirTree.type === Type.file) {
    return result;
  }

  if (unusedSpaceIfDeleted >= requiredSpace) {
    let chosen: Node | undefined = undefined;
    for (const [_, value] of dirTree.children!) {
      let childrenResult = findSmallestDirToRemove(
        value,
        requiredSpace,
        unusedSpace
      );
      if (childrenResult) {
        if (!chosen) {
          chosen = childrenResult;
        } else {
          chosen = chosen.size < childrenResult.size ? chosen : childrenResult;
        }
      }
    }

    if (chosen) {
      result = chosen;
    } else {
      result = dirTree;
    }
  }

  return result;
}

function executeList(inpLines: string[], ind: number, pointer: Node) {
  let sizeSum = 0;

  let tokens = inpLines[ind].split(" ");
  let i: number;
  for (i = ind; i < inpLines.length && tokens[0] !== "$"; i++) {
    if (tokens[0] === "$" || i === inpLines.length) {
    }

    if (tokens[0] === "dir") {
      pointer.children!.set(
        tokens[1],
        new Node(tokens[1], Type.directory, 0, pointer)
      );
    } else {
      let size = Number(tokens[0]);
      sizeSum += size;
      pointer.children!.set(
        tokens[1],
        new Node(tokens[1], Type.file, size, pointer)
      );
    }

    tokens = inpLines[i + 1]?.split(" ");
  }

  pointer.size = sizeSum;
  updateParentsSize(pointer, sizeSum);
  return i - 1;
}

function buildDirectoryTree(inpLines: string[]): Node {
  const directoryTree: Node = new Node("/", Type.directory, 0);
  let currentPointer: Node = directoryTree;

  for (let i = 0; i < inpLines.length; i++) {
    let words = inpLines[i].split(" ");
    if (words[1] === "cd") {
      if (words[2] === "/") {
        currentPointer = directoryTree;
      } else if (words[2] === "..") {
        currentPointer = currentPointer.parent ?? currentPointer;
      } else {
        let directory = words[2];
        currentPointer = currentPointer.children?.get(directory)!;
      }
    } else if (words[1] === "ls") {
      i = executeList(inpLines, i + 1, currentPointer);
    }
  }

  return directoryTree;
}

export function Part1() {
  // const inp = help.readShortInputByDay(7);
  const inp = help.readInputByDay(7);
  const inpLines = inp.split("\n");
  const directoryTree = buildDirectoryTree(inpLines);
  const result = sumAllDirSizesBelow(directoryTree, 100000);

  console.log("Part 1:", result);
}

export function Part2() {
  // const inp = help.readShortInputByDay(7);
  const inp = help.readInputByDay(7);

  const inpLines = inp.split("\n");
  const directoryTree = buildDirectoryTree(inpLines);
  const result = findSmallestDirToRemove(
    directoryTree,
    30000000,
    70000000 - directoryTree.size
  );

  console.log("Part 2:", result?.size);
}

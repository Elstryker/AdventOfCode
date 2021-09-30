package AOC2020.Day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataGraph {
    HashMap<String,ArrayList<String>> childrenToParentGraph;
    HashMap<String,ArrayList<MyPair<Integer,String>>> parentToChildrenGraph;

    public DataGraph() {
        childrenToParentGraph = new HashMap<>();
        parentToChildrenGraph = new HashMap<>();
    }

    public void addChildrenToParent(String motherBag, ArrayList<String> children) {
        childrenToParentGraph.putIfAbsent(motherBag,new ArrayList<>());

        for(String child : children) {

            var parents = childrenToParentGraph.get(child);

            if(parents == null)
            {
                ArrayList<String> newParents = new ArrayList<>();
                newParents.add(motherBag);
                childrenToParentGraph.put(child,newParents);
            }
            else
            {
                parents.add(motherBag);
            }
        }
    }

    public void addParentToChildren(String motherBag, ArrayList<MyPair<Integer,String>> childrenBags) {
        for(var child : childrenBags) {
            var graphChildren = parentToChildrenGraph.get(motherBag);

            if(graphChildren == null)
            {
                ArrayList<MyPair<Integer,String>> newChildren = new ArrayList<>();
                newChildren.add(child);
                parentToChildrenGraph.put(motherBag,newChildren);
            }
            else
            {
                graphChildren.add(child);
            }
        }
    }

    public int searchAllParentBags() {
        HashSet<String> visited = new HashSet<>();
        visited.add("shiny gold");
        int totalBagColorParents = 0;

        var shinyGoldBagParents = childrenToParentGraph.get("shiny gold");

        for(String parent : shinyGoldBagParents) {
            totalBagColorParents += recursiveSearchParent(parent,visited);
        }

        return totalBagColorParents;
    }

    private int recursiveSearchParent(String parent, HashSet<String> visited) {
        int currentParentCount = 0;

        if(!visited.contains(parent)) {
            visited.add(parent);
            var currentParents = childrenToParentGraph.get(parent);
            currentParentCount++;

            for(var currentParent : currentParents) {
                currentParentCount += recursiveSearchParent(currentParent, visited);
            }
        }

        return currentParentCount;
    }

    public int searchAllChildrenBags() {
        int totalChildrenBags = 0;

        var shinyGoldBagChildren = parentToChildrenGraph.get("shiny gold");

        for(var child : shinyGoldBagChildren) {
            int multiplier = child.first;
            totalChildrenBags += multiplier;
            totalChildrenBags += recursiveSearchChild(child.second,multiplier);
        }

        return totalChildrenBags;
    }

    private int recursiveSearchChild(String parent, int multiplier) {
        int currentChildCount = 0;
        var currentChildren = parentToChildrenGraph.get(parent);

        if(currentChildren != null) {
            int totalNumber = 0;

            for (var currentChild : currentChildren) {
                totalNumber += currentChild.first;
                currentChildCount += recursiveSearchChild(currentChild.second, multiplier * currentChild.first);
            }

            currentChildCount += totalNumber * multiplier;
        }

        return currentChildCount;
    }
}

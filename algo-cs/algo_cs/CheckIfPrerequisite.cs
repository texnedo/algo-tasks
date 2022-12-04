using System;
namespace algo_cs
{
    public class Node
    {
        public readonly int index;
        public readonly List<int> links = new List<int>();

        public Node(int i)
        {
            index = i;
        }
    }

    public class CheckIfPrerequisiteTask
    {
        public CheckIfPrerequisiteTask()
        {
        }

        public static IList<bool> CheckIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries)
        {
            return CheckIfPrerequisite(numCourses, To2D(prerequisites), To2D(queries));
        }

        public static IList<bool> CheckIfPrerequisite(int numCourses, int[,] prerequisites, int[,] queries)
        {
            Dictionary<int, Node> nodes = new Dictionary<int, Node>();
            for (int i = 0; i < prerequisites.Length / prerequisites.Rank; i++)
            {
                int start = prerequisites[i, 0];
                int end = prerequisites[i, 1];
                Node node;
                if (!nodes.ContainsKey(start))
                {
                    node = new Node(start);
                    nodes[start] = node;
                }
                else
                {
                    node = nodes[start];
                }
                node.links.Add(end);
                if (!nodes.ContainsKey(end))
                {
                    nodes[end] = new Node(end);
                }
            }
            var result = Dfs(nodes);
            var answers = new List<bool>();
            for (int i = 0; i < queries.Length / queries.Rank; i++)
            {
                int start = queries[i, 0];
                int end = queries[i, 1];
                if (!result.ContainsKey(end))
                {
                    answers.Add(false);
                }
                else
                {
                    var prereqisites = result[end];
                    if (prereqisites.Contains(start))
                    {
                        answers.Add(true);
                    }
                    else
                    {
                        answers.Add(false);
                    }
                }
            }
            return answers;
        }

        private static T[,] To2D<T>(T[][] source)
        {
            try
            {
                int FirstDim = source.Length;
                int SecondDim = source.GroupBy(row => row.Length).Single().Key; // throws InvalidOperationException if source is not rectangular

                var result = new T[FirstDim, SecondDim];
                for (int i = 0; i < FirstDim; ++i)
                    for (int j = 0; j < SecondDim; ++j)
                        result[i, j] = source[i][j];

                return result;
            }
            catch (InvalidOperationException)
            {
                throw new InvalidOperationException("The given jagged array is not rectangular.");
            }
        }

        private static Dictionary<int, HashSet<int>> Dfs(Dictionary<int, Node> nodes)
        {
            var result = new Dictionary<int, HashSet<int>>();
            foreach (var item in nodes)
            {
                DfsRun(nodes, item.Value, new HashSet<int>(), result);
            }
            return result;
        }

        private static void DfsRun(Dictionary<int, Node> nodes,
                                   Node current,
                                   HashSet<int> path,
                                   Dictionary<int, HashSet<int>> result)
        {
            foreach (var item in current.links)
            {
                path.Add(current.index);
                DfsRun(nodes, nodes[item], path, result);
                path.Remove(current.index);
            }
            HashSet<int> merged;
            if (result.ContainsKey(current.index))
            {
                merged = result[current.index];
            }
            else
            {
                merged = new HashSet<int>();
                result[current.index] = merged;
            }
            foreach (int item in path)
            {
                merged.Add(item);
            }
        }
    }
}


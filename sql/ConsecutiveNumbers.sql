CREATE TABLE Logs (Id int, Num int);

INSERT INTO Logs (Id, Num)
    VALUES
    (1,1),
    (2,1),
    (3,1),
    (4,2),
    (5,1),
    (6,2),
    (7,2);

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+


SELECT DISTINCT l12.Num as ConsecutiveNums FROM
(SELECT l1.Id, l1.Num FROM Logs l1 INNER JOIN Logs l2 ON (l1.Id = l2.Id + 1 AND l1.Num = l2.Num)) as l12
    INNER JOIN (SELECT l1.Id, l1.Num FROM Logs l1 INNER JOIN Logs l2 ON (l1.Id = l2.Id + 1 AND l1.Num = l2.Num)) as l23 ON (l12.Id = l23.Id + 1 AND l12.Num = l23.Num)
% Advanced Prolog Family Tree Queries
% This file demonstrates more complex queries and logical operations

% Load the family tree program
:- consult('family_tree.pl').

% Advanced Query Examples:

% 1. Find all females who are parents
% Query: female(X), parent(X, _).

% 2. Find all males who have at least two children
% Query: male(X), parent(X, Y), parent(X, Z), Y \= Z.

% 3. Find all people who are both parents and grandparents
% Query: parent(X, _), grandparent(X, _).

% 4. Find all pairs of siblings who are both parents
% Query: sibling(X, Y), parent(X, _), parent(Y, _).

% 5. Find all people who have siblings and are also parents
% Query: sibling(X, _), parent(X, _).

% 6. Find people who have no siblings
% This requires negation:
% Query: \+ sibling(X, _).

% 7. Find all ancestors of Tom in ascending order of generation
% Query: ancestor(X, tom).

% 8. Find all descendants of John who are female
% Query: descendant(X, john), female(X).

% 9. Find the common ancestors of Tom and Sara
% Query: ancestor(X, tom), ancestor(X, sara).

% 10. Find all cousin relationships
% Query: cousin(X, Y).

% 11. Find people who are both uncles and fathers
% Query: uncle(X, _), father(X, _).

% 12. Find people who have at least one sibling and one cousin
% Query: sibling(X, _), cousin(X, _).

% 13. Find all grandparents and their grandchildren
% Query: grandparent(X, Y).

% 14. Using findall to collect all children of a person
% Query: findall(Child, parent(john, Child), Children).

% 15. Using count to find how many children each person has
% First define a helper predicate:
count_children(Person, Count) :- findall(Child, parent(Person, Child), Children), length(Children, Count).
% Then query: count_children(john, X).

% 16. Finding the generation count between ancestor and descendant
generation_distance(Ancestor, Descendant, 1) :- parent(Ancestor, Descendant).
generation_distance(Ancestor, Descendant, N) :- parent(Ancestor, Intermediate), generation_distance(Intermediate, Descendant, M), N is M + 1.
% Query: generation_distance(john, emily, Gen).

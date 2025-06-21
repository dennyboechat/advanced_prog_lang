% Family Tree Queries
% This file contains example queries for the family tree program

% Load the family tree program
:- consult('family_tree.pl').

% Example Queries:

% 1. Find all parents
% Query: parent(X, Y).

% 2. Find the children of John
% Query: parent(john, X).

% 3. Find the parents of Bob
% Query: parent(X, bob).

% 4. Find all siblings of Lisa
% Query: sibling(X, lisa).

% 5. Find if Bob and Lisa are siblings
% Query: sibling(bob, lisa).

% 6. Find all grandparents of Jim
% Query: grandparent(X, jim).

% 7. Find all grandchildren of John
% Query: grandparent(john, X).

% 8. Find all cousins of Ann
% Query: cousin(X, ann).

% 9. Find if Tom and Sara are cousins
% Query: cousin(tom, sara).

% 10. Find all ancestors of Emily
% Query: ancestor(X, emily).

% 11. Find all descendants of John
% Query: descendant(X, john).

% 12. Find all uncles of Tom
% Query: uncle(X, tom).

% 13. Find all aunts of Carol
% Query: aunt(X, carol).

% To run a query in Prolog:
% 1. Start the Prolog interpreter (e.g., swipl)
% 2. Load this file: ?- consult('family_queries.pl').
% 3. Type any of the example queries above at the Prolog prompt
% 4. Use semicolon (;) to see more solutions or period (.) to stop

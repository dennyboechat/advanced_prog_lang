% Family Tree Program in Prolog
% This program represents family relationships and allows queries about
% parent, grandparent, sibling, and cousin relationships.

% Basic facts: parent(Parent, Child)
parent(john, bob).
parent(john, lisa).
parent(mary, bob).
parent(mary, lisa).
parent(bob, ann).
parent(bob, pat).
parent(lisa, jim).
parent(lisa, carol).
parent(pat, tom).
parent(ann, sara).
parent(jim, emily).
parent(carol, mike).

% Gender facts
male(john).
male(bob).
male(jim).
male(pat).
male(tom).
male(mike).
female(mary).
female(lisa).
female(ann).
female(carol).
female(sara).
female(emily).

% Derived relationships

% Father relationship
father(X, Y) :- parent(X, Y), male(X).

% Mother relationship
mother(X, Y) :- parent(X, Y), female(X).

% Grandparent relationship
grandparent(X, Z) :- parent(X, Y), parent(Y, Z).

% Grandfather relationship
grandfather(X, Z) :- father(X, Y), parent(Y, Z).

% Grandmother relationship
grandmother(X, Z) :- mother(X, Y), parent(Y, Z).

% Sibling relationship
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

% Brother relationship
brother(X, Y) :- sibling(X, Y), male(X).

% Sister relationship
sister(X, Y) :- sibling(X, Y), female(X).

% Uncle relationship
uncle(X, Y) :- parent(Z, Y), brother(X, Z).

% Aunt relationship
aunt(X, Y) :- parent(Z, Y), sister(X, Z).

% Cousin relationship
cousin(X, Y) :- parent(Z, X), parent(W, Y), sibling(Z, W).

% Ancestor relationship (recursive)
ancestor(X, Y) :- parent(X, Y).
ancestor(X, Y) :- parent(X, Z), ancestor(Z, Y).

% Descendant relationship (recursive)
descendant(X, Y) :- parent(Y, X).
descendant(X, Y) :- parent(Z, X), descendant(Z, Y).

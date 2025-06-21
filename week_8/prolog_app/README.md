# Family Tree Program in Prolog

This is a simple Family Tree Program implemented in Prolog that represents relationships between family members and allows querying various family relationships.

## How to Use

1. Install SWI-Prolog or another Prolog interpreter
2. Start the Prolog interpreter from the command line:
   ```
   swipl
   ```
3. Load the family tree program:
   ```
   ?- consult('family_tree.pl').
   ```
4. Or load the queries file (which will also load the main program):
   ```
   ?- consult('family_queries.pl').
   ```
5. Run queries at the Prolog prompt. For example:
   ```
   ?- parent(john, X).
   X = bob ;
   X = lisa.
   ```
   
   ```
   ?- sibling(bob, lisa).
   true.
   ```
   
   ```
   ?- cousin(tom, sara).
   true.
   ```

6. Press `;` to see more solutions (if available) or `.` to stop

## Adding More Family Members

You can extend the family tree by adding more facts to the `family_tree.pl` file:

```prolog
parent(new_parent, new_child).
male(new_person).  % or female(new_person).
```

## Sample Queries

- Find all children of a person: `parent(person_name, X).`
- Find all siblings of a person: `sibling(X, person_name).`
- Check if two people are cousins: `cousin(person1, person2).`
- Find all ancestors of a person: `ancestor(X, person_name).`
- Find all descendants of a person: `descendant(X, person_name).`

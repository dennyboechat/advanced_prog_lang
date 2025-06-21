#!/bin/bash
# This script demonstrates how to run Prolog queries on the family tree program

echo "Running Family Tree Prolog Program"
echo "=================================="
echo ""

# Check if swipl is installed
if ! command -v swipl &> /dev/null
then
    echo "SWI-Prolog is not installed."
    echo "Please follow the instructions in INSTALL.md to install it."
    exit 1
fi

# Run some example queries
echo "Sample Query 1: Who are the children of John?"
swipl -q -s family_tree.pl -g "parent(john, X), write(X), nl, fail; halt." 2>/dev/null || true
echo ""

echo "Sample Query 2: Are Bob and Lisa siblings?"
swipl -q -s family_tree.pl -g "sibling(bob, lisa), write('Yes, they are siblings.'), nl, halt." 2>/dev/null || echo "No, they are not siblings."
echo ""

echo "Sample Query 3: Who are the grandchildren of John?"
swipl -q -s family_tree.pl -g "grandparent(john, X), write(X), nl, fail; halt." 2>/dev/null || true
echo ""

echo "Sample Query 4: Are Tom and Sara cousins?"
swipl -q -s family_tree.pl -g "cousin(tom, sara), write('Yes, they are cousins.'), nl, halt." 2>/dev/null || echo "No, they are not cousins."
echo ""

echo "Sample Query 5: Who are all the descendants of John?"
swipl -q -s family_tree.pl -g "descendant(X, john), write(X), nl, fail; halt." 2>/dev/null || true
echo ""

echo "Try running more queries by starting the Prolog interpreter:"
echo "  swipl -s family_tree.pl"
echo ""
echo "Or load the example queries files:"
echo "  swipl -s family_queries.pl"
echo "  swipl -s advanced_queries.pl"

fn main() {
    let boxed_int = Box::new(1);
    println!("Boxed int: {}", boxed_int);

    let borrowed_ref = &boxed_int;
    println!("Borrowed ref: {}", borrowed_ref);

    let moved_box = boxed_int;
    println!("Moved box: {}", moved_box);
}
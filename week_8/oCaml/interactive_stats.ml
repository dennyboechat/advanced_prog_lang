(* Basic statistics calculator in OCaml with user input *)

(* Calculate the mean of a list *)
let mean lst =
  match lst with
  | [] -> None
  | _ -> 
      let sum = List.fold_left (+) 0 lst in
      let count = List.length lst in
      Some (float_of_int sum /. float_of_int count)

(* Calculate the median of a list *)
let median lst =
  match lst with
  | [] -> None
  | _ ->
      let sorted = List.sort compare lst in
      let len = List.length sorted in
      if len mod 2 = 1 then
        (* Odd length list - return middle element *)
        Some (float_of_int (List.nth sorted (len / 2)))
      else
        (* Even length list - return average of two middle elements *)
        let middle1 = List.nth sorted ((len / 2) - 1) in
        let middle2 = List.nth sorted (len / 2) in
        Some (float_of_int (middle1 + middle2) /. 2.0)

(* Count occurrences of each element in the list *)
let count_occurrences lst =
  let increment_count map x =
    let count = try List.assoc x map with Not_found -> 0 in
    (x, count + 1) :: List.remove_assoc x map
  in
  List.fold_left increment_count [] lst

(* Calculate the mode of a list *)
let mode lst =
  match lst with
  | [] -> None
  | _ ->
      let occurrences = count_occurrences lst in
      (* Find the maximum frequency *)
      let max_freq = 
        List.fold_left (fun acc (_, count) -> max acc count) 0 occurrences 
      in
      (* Filter elements with the maximum frequency *)
      let modes = 
        List.filter (fun (_, count) -> count = max_freq) occurrences
        |> List.map fst
      in
      Some modes

(* Calculate all statistics for a list *)
let calculate_stats lst =
  (mean lst, median lst, mode lst)

(* Parse a string of space-separated integers *)
let parse_int_list str =
  let elements = String.split_on_char ' ' str in
  let filtered = List.filter (fun s -> String.length s > 0) elements in
  List.map int_of_string filtered

(* Print statistics for a list *)
let print_stats lst =
  Printf.printf "List: [";
  List.iter (fun x -> Printf.printf "%d; " x) lst;
  Printf.printf "]\n";
  
  let (mean_result, median_result, mode_result) = calculate_stats lst in
  
  (match mean_result with
  | Some m -> Printf.printf "Mean: %.2f\n" m
  | None -> Printf.printf "Mean: N/A (empty list)\n");
  
  (match median_result with
  | Some m -> Printf.printf "Median: %.2f\n" m
  | None -> Printf.printf "Median: N/A (empty list)\n");
  
  (match mode_result with
  | Some m -> 
      Printf.printf "Mode: [";
      List.iter (fun x -> Printf.printf "%d; " x) m;
      Printf.printf "]\n"
  | None -> Printf.printf "Mode: N/A (empty list)\n")

(* Main program *)
let () =
  print_endline "OCaml Statistics Calculator";
  print_endline "---------------------------";
  print_endline "Enter integers separated by spaces, or press Enter to quit:";
  
  let rec process_input () =
    flush stdout;
    let line = read_line () in
    if line = "" then
      print_endline "Goodbye!"
    else
      try
        let int_list = parse_int_list line in
        print_stats int_list;
        print_endline "\nEnter another list or press Enter to quit:";
        process_input ()
      with
      | Failure _ -> 
          print_endline "Invalid input. Please enter integers separated by spaces.";
          process_input ()
  in
  
  try
    process_input ()
  with
  | End_of_file -> print_endline "Goodbye!"

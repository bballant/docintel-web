drop table if exists questions;

CREATE TABLE questions (
  id integer primary key asc,
  number int not null,
  unit int not null,
  title char(200) not null,
  question text not null,
  explanation text not null,
  code_path char(200) not null,
  deleted int );

insert into questions (number, unit, title, question, explanation, code_path)
  values (1, 1, "Odd Number",
    "I have an integer array where every number appears even number of times and only one appears odd times. Find the number.",
    "XOR all the numbers together. The final result of XOR is your answer. O(1) space, O(n) time.",
    "/scala-code/1/1-Odd_Number/OddNumber.scala");

insert into questions (number, unit, title, question, explanation, code_path)
  values (2, 1, "Palindrome Bits",
    "Find if a binary representation of a number is palindrome. The function should work irrespective of number of bytes for an integer. Suppose if our machine is 4 bytes for an int, how will you use the program for 8 byte machine.",
    "Reverse the bits of the given integer and store it in another variable. Now check both the numbers whether they are equal or not. If equal the given number is palindrome otherwise not.",
    "/scala-code/1/2-Palindrome_Bits/PalindromeBits.scala");

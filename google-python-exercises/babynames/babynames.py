#!/usr/bin/python
# Copyright 2010 Google Inc.
# Licensed under the Apache License, Version 2.0
# http://www.apache.org/licenses/LICENSE-2.0

# Google's Python Class
# http://code.google.com/edu/languages/google-python-class/

import sys
import re

"""Baby Names exercise

Define the extract_names() function below and change main()
to call it.

For writing regex, it's nice to include a copy of the target
text for inspiration.

Here's what the html looks like in the baby.html files:
...
<h3 align="center">Popularity in 1990</h3>
....
<tr align="right"><td>1</td><td>Michael</td><td>Jessica</td>
<tr align="right"><td>2</td><td>Christopher</td><td>Ashley</td>
<tr align="right"><td>3</td><td>Matthew</td><td>Brittany</td>
...

Suggested milestones for incremental development:
 -Extract the year and print it
 -Extract the names and rank numbers and just print them
 -Get the names data into a dict and print it
 -Build the [year, 'name rank', ... ] list and print it
 -Fix main() to use the extract_names list
"""
def name_with_rank(name, rank):
    return name + ' ' + rank

def extract_names(filename):
  """
  Given a file name for baby.html, returns a list starting with the year string
  followed by the name-rank strings in alphabetical order.
  ['2006', 'Aaliyah 91', Aaron 57', 'Abagail 895', ' ...]
  """

  print('processing ', filename)
  f = open(filename, 'r')
  file_content = f.read()

  #extract year
  match = match=re.search(r'(name="year".+value=")(\d+)"', file_content)
  if match:
      year = match.group(2)
  else:
      sys.stderr.write('Year not found for this file, exiting\n')
      sys.exit(1)

  #extract name and rank
  name_rank_match = re.findall(r'<td>(\d+).+<td>(\w+).+<td>(\w+).+\n', file_content)
  names_with_ranks = []
  if name_rank_match:
      for name in name_rank_match:
          names_with_ranks.append(name_with_rank(name[1],name[0]))
          names_with_ranks.append(name_with_rank(name[2],name[0]))
  sorted_array = sorted(names_with_ranks)
  sorted_array.insert(0, year)

  f.close()
  return sorted_array


def main():
  # This command-line parsing code is provided.
  # Make a list of command line arguments, omitting the [0] element
  # which is the script itself.
  args = sys.argv[1:]

  if not args:
    print ('usage: [--summaryfile] file [file ...]')
    sys.exit(1)

  # Notice the summary flag and remove it from args if it is present.
  summary = False
  if args[0] == '--summaryfile':
    summary = True
    del args[0]

  # +++your code here+++
  # For each filename, get the names, then either print the text output
  # or write it to a summary file
  for filename in args :
      names_with_ranks = extract_names(filename)
      if summary:
          summaryfile = open(filename + '.summary', 'w')
          summaryfile.write(str(names_with_ranks) + '\n')
          summaryfile.close()
      else:
          print(names_with_ranks)

if __name__ == '__main__':
  main()

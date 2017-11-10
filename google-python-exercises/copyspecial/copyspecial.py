#!/usr/bin/python
# Copyright 2010 Google Inc.
# Licensed under the Apache License, Version 2.0
# http://www.apache.org/licenses/LICENSE-2.0

# Google's Python Class
# http://code.google.com/edu/languages/google-python-class/

import sys
import re
import os
import shutil
import zipfile

"""Copy Special exercise
"""

""" The goal of this program is to copy all files that are contained in
the provided list of directories to another directory or zip them to a zip file"""
# +++your code here+++
# Write functions and modify main() to call them

# a special file is one with __w+__ pattern
def find_special_files(from_dirs):
    special_files = []

    for directory in from_dirs:
        # skip non existing directories
        if os.path.exists(directory):
            filenames = os.listdir(directory)
            for name in filenames:
                special_file_match = re.search(r'\w*__\w+__.*', name)
                if special_file_match:
                    special_files.append(os.path.join(directory, special_file_match.group(0)))
    return special_files

def copy_to_dir(from_dirs, todir):
    print("copying files from " + str(from_dirs) + "to " + str(todir))
    if not os.path.exists(todir):
        os.mkdir(todir)

    files_to_copy = find_special_files(from_dirs)

    for file_to_copy in files_to_copy:
        print("processing file" + filename)
        shutil.copy(file_to_copy, todir)

def zip_to_file(from_dirs, tozip):
    print("zipping files from " + str(from_dirs) + "to " + str(tozip))
    files_to_copy = find_special_files(from_dirs)

    zip_file = zipfile.ZipFile(tozip,'w')

    for filename in files_to_copy:
        print("processing file" + filename)
        zip_file.write(filename)

    zip_file.close()

def main():
  # This basic command line argument parsing code is provided.
  # Add code to call your functions below.

  # Make a list of command line arguments, omitting the [0] element
  # which is the script itself.
  args = sys.argv[1:]
  if not args:
    print("usage: [--todir dir][--tozip zipfile] dir [dir ...]");
    sys.exit(1)

  # todir and tozip are either set from command line
  # or left as the empty string.
  # The args array is left just containing the dirs.
  todir = ''
  if args[0] == '--todir':
    todir = args[1]
    del args[0:2]

  tozip = ''
  if args[0] == '--tozip':
    tozip = args[1]
    del args[0:2]

  if len(args) == 0:
    print("error: must specify one or more dirs")
    sys.exit(1)

  if todir:
      copy_to_dir(args, todir)
  elif tozip:
      zip_to_file(args,tozip)
  else:
      print("error: must specify either --todir or --tozip arguments")

  # +++your code here+++
  # Call your functions

if __name__ == "__main__":
  main()

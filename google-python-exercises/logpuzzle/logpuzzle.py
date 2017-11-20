#!/usr/bin/python
# Copyright 2010 Google Inc.
# Licensed under the Apache License, Version 2.0
# http://www.apache.org/licenses/LICENSE-2.0

# Google's Python Class
# http://code.google.com/edu/languages/google-python-class/

import os
import re
import sys
import urllib.request

"""Logpuzzle exercise
Given an apache logfile, find the puzzle urls and download the images.

Here's what a puzzle url looks like:
10.254.254.28 - - [06/Aug/2007:00:13:48 -0700] "GET /~foo/puzzle-bar-aaab.jpg HTTP/1.0" 302 528 "-" "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
"""

def read_hostname_from_file(filename):
    return 'http://' + str(re.search(r'_(.*)',filename).groups(0)[0])

def build_sort_key(img_url):
    match = re.findall(r'.+/.*-(\S+).jpg',img_url)
    if len(match) == 1:
        return match[0]
    else:
        return img_url

def read_urls(filename):
  """Returns a list of the puzzle urls from the given log file,
  extracting the hostname from the filename itself.
  Screens out duplicate urls and returns the urls sorted into
  increasing order."""
  print('reading urls from file ', filename)
  host_url = read_hostname_from_file(filename)
  f = open(filename, 'r')
  file_content = f.read()
  
  print('filecontent ' + file_content)
  
  urls = sorted(list(set(re.findall(r'GET (\S+.jpg) HTTP',file_content))), key=build_sort_key)
  
  f.close()
  
  return [host_url + url for url in urls]
  
def absolute_img_path(img_name, dest_dir):
    return str(os.path.abspath(dest_dir) + '/' + img_name)

def build_img_name(img_number):
    return 'img' + str(img_number) + '.jpg'

def html_for_img(img_name):
    return '<img style="float:left" src="' + img_name + '"/>'

def html_skeleton():
    return """
            <!DOCTYPE html>\n
            <html>\n
            <head>\n
                <title>google python exercise - Logpuzzle </title>\n
            </head>\n
            <body>\n
                <div>
                    IMG_PLACEHOLDER
                </div>
            </body>\n
            </html>\n
            """

def download_images(img_urls, dest_dir):
  """Given the urls already in the correct order, downloads
  each image into the given directory.
  Gives the images local filenames img0, img1, and so on.
  Creates an index.html in the directory
  with an img tag to show each local image file.
  Creates the directory if necessary.
  """
  print('downloading images to ' + str(dest_dir))
  if not os.path.exists(dest_dir):
      os.mkdir(dest_dir)
  
  html_index_file = open(dest_dir + '/index.html','w')
  
  img_counter = 0
  
  html_for_images = ''
  
  for img in img_urls:
       img_name = build_img_name(img_counter)
       print('saving ' + str(img) + ' with name ' + img_name)
       try:
           urllib.request.urlretrieve(img, absolute_img_path(img_name,dest_dir))
           html_for_images = html_for_images + html_for_img(img_name) + '\n'
           img_counter = img_counter + 1
       except urllib.error.HTTPError:
           print('error while retrieving ' + img + '..skipping')

  print('writing output to index.html file')
  html_index_file.write(html_skeleton().replace('IMG_PLACEHOLDER', html_for_images))
  html_index_file.close()
       

def main():
  args = sys.argv[1:]

  if not args:
    print ('usage: [--todir dir] logfile ')
    sys.exit(1)

  todir = ''
  if args[0] == '--todir':
    todir = args[1]
    del args[0:2]

  img_urls = read_urls(args[0])

  if todir:
    download_images(img_urls, todir)
  else:
    print ('\n'.join(img_urls))

if __name__ == '__main__':
  main()

# SimpleSearch

## Usage

Use Maven to build jar
```bash
mvn clean install
```

To run program move to the target directory and execute it with command
```bash
java -jar SimpleSearch.jar Searcher /directory
```
## Overview
SimpleSearch is a command-line program that allow user to search all text files in provided directory by words from entered phrase.
Files are stored in the memory in a Map with filename as key and content as value. 
Search is based on words entered by a user. It is case sensitive, but words order is not taken into account, as well as punctuation marks and number of occurrences.
Ranking algorithm is very simple. It checks how many of words from search phrase was present in file and this number is divided by total number of words in the search phrase.


## Possible improvements

- Currently Searcher is able to read only UTF-8 files, but it can be extended to other encodings.
- Adding result list size as program parameter is an easy improvement thanks to ResultsCompiler that accepts number of results as an argument.
- Thanks to decomposition of FileReader it's possible to prepare an alternative.
- RankEvaluator can be replaced with more advanced solution or it could be implemented in several ways, that can be selected at runtime.
- For bigger files or greater amount of them I would recommend to change in memory file map to reading files line by line or earlier prepared list of words per file.

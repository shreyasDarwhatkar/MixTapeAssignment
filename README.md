# MixTapeAssignment
This project is made for the sole purpose of HighSpot Assignment


## Goal:
We are given the input file called as *Mixtap.json* which contains the list of users, songs and playlists,
there is one more input file *changes.json* file this file contains the changes that we need to apply to *Mixtap.json* file.

## Prerequisite
* Java > 1.7
* Gradle >=6.5
* Macos

### Structure of the changes.json file
The Mixtap.json file is already provided to us so here we will only discuss about the structure of the changes.json file which is designed by me.

 *The changes.json file contains the list of changes, below is the structure of the changes.json file*
```
{
  "operations": [{
    "operation_type": "add_song",
    "song_id": "10",
    "playlist_id": "2"
  },
    {
      "operation_type": "create_playlist",
      "playlist": {
        "id": "6",
        "user_id": "3",
        "song_ids": [
          "6",
          "7",
          "13"
        ]
      }
    },
    {
      "operation_type": "remove_playlist",
      "playlist_id": "1"
    }
  ]
}
```

In the above section the operations is the array of operation that we want to perform on the input file mixtape.json
operation_type specifies the type of operation that we need to perform on the mixtape.json
there are three types of operation

* **create_playlist** : Creates a new playlist
* **add_song** : Add a existing song to a existing playlist
* **remove_playlist**: Remove the existing playlist 




### How to run this Project
**Download the project using the below command**

```
git clone https://github.com/shreyasDarwhatkar/MixTapeAssignment.git
```
**CD into the project folder**
```
cd MixTapeAssignment
```
**Run gradle build**
```
./gradlew build
```
**Run below command to run the with the mixtape input file, changes file and the output file**
```
./gradlew run --args="--input-file=<path of the input file> --changes-file=<path of the changes file> --output-file=<path for the output JSon file>
```

*Example*
```
./gradlew run --args="--input-file=src/main/resources/mixtape.json --changes-file=src/main/resources/changes.json --output-file=output.json"
```
If you are in the the directory /MixTapeAssignment/ you can even directly run ./gradlew run as I have provided the default path, in this case it will take the file kept in path rc/main/resources/*

For the sole purpose of making the verification easy I have written all the result directly on the console itself.

### Scalability Discussion

Currently we are able to read the complete mixtape.json  file into the memory and changes file as they are small
but if they grow large we will have change our reading logic.

#### Possible Solution
As we cannot read the complete Mixtape.json file into the memory we will have to read the file in chunks, to do we can maintain a offset value that will store the value till what 
point we have completed reading the json file, everytime our program will check this offset value and will load the remaining file accordingly in the memory.

now the problem is what if the changes we need to apply on mixtape playlist/song is in previous chunk of data that we have done reading already?
so we need to persist the data that we have read from the mixtape.json file.

To solve this we can perform indexing and create a small file database on the machine,

* 1.Read the file in chunk, create the user, playlist, and songs list and sort them by the id's
* 2.Write the chunk of data in the new file and maintain a hashmap mentioning the range of ids and the name of the file 
  * for E.g lets call this maps as **directory maps**
  ```
  
  HashMap userName : key = Id Range & Value = file path 
  HashMap playList : key = Id Range & Value = file path
  HashMap songs : key = Id Range & Value = file path
  ``` 
* 3.If the changes.json file is too large we are going to follow the same pattern as we followed in the mixtape.json file, We will read the file in chunks using 
the offset value and after performing the operations we can discard the chunk/content as that will be of no use.        

so if both the mixtape.json and changes.json both this files are too large we will follow the below flow
* 1.Read the mixtape.json in chunks in the hashmap sort them by id's.
* 2.Write the chunk in the small files, and maintain  the range of the id and the file path
* 3.Read the changes.json file in chunks,load the operations in the operation list, find the respective id and file from the hashmap, make the change and write back.
* 4.For e.g consider if the first operation is add_song, so here we will have the playlist ID and the songID that we want to add in the playlist,
so first we will search the directory maps and find out the file which will contains the info of the playlist and the song that we are looking for we can load this files in the memory and then perform the changes
after that we can write it back
* 5.Creating the output.json file, now to create the output.json file we will have to read the chunk files and write it back into a one large file and delete all our temporary chunk files.  


 






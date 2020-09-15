# MixTapeAssignment
This project is made for the sole purpose of HighSpot Assignment


## Goal:
We are given the input file called as *Mixtap.json* which contains the list if users, songs and playlists,
there is one more input file *changes.json* file this file contains the changes that we need to apply to *Mixtap.json* file.

## Prerequisite
* Java > 1.7
* Gradle 
* Macos

### Structure of the changes.json file
The Mixtap.json file is already provided to us so here we will only discuss about the structure of the changes of JSON file which is designed by me.

 *The changes file contains the list of changes, below is the structure of the changes file*
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







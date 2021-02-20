# Selection Filter

> Exercise 2.4.28
> Write a program similar to TopM that reads points (x, y, z) from standard 
> input, takes a value M from the command line, and prints the M points that
> are closest to the origin in Euclidean distance. Estimate the running time
> of your client N = 10^8 and M=10^4

## How to run

1.Save scripts' directory into a variable:
```shell
 SCRIPTS_HOME="$(dirname "$(find . -type f -name "selectionFilterRunner.sh" | head -n 1)")"
```
2. Generate and store in a file a number of coordinate points, e.g.:
```shell
bash -c "$SCRIPTS_HOME/generateNPoints.sh 100 --output-location=/home/$USER"
```
The preceding script will create a file `points.txtx` consisting of 100 random 
three-dimensional coordinate points and put in your home directory.
> Please examine `generateNPoints.sh` for additional script options and default 
> values
3. Run the `SelectionFilter` app:
```shell
bash -c "$SCRIPTS_HOME/selectionFilterRunner.sh --input-file=/home/$USER/points.txt"
```
Notice the necessity to have `sbt` installed locally.
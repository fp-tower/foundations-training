![FP Foundation Logo](logo/Foundation.png)<br>

## Getting Started

#### 1. Clone the project

```bash
git clone git@github.com:fp-tower/foundations-training.git
// or
git clone https://github.com/fp-tower/foundations-training.git
``` 

#### 2. Run the tests

```bash
sbt test
```

If you don't have `sbt`, you can find instructions [here](https://www.scala-sbt.org/download.html).


#### 3. Open your favorite IDE

Open the file `exercises/function/ValueFunctionExercises.scala` and check your Scala plugin is working.

## SBT 101

Start the sbt shell in your terminal by running `sbt`.

| Actions           | Command to run inside sbt shell |
|-------------------|---------------------------------|
| Compile           | exercises/compile               |
| Run tests         | exercises/test                  | 
| Run test files | exercises/testOnly \*ValueFunctionEx\*   |
| Run specific tests    | exercises/testOnly \*ValueFunctionEx\* -- -z "selectDigits" |


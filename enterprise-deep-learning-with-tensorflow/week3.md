# Enterprise deep learning with Tensorflow - week 3

## The need for deeper networks

Limitations for shallow networks:
- very good at memorizing the data, and therefore might lead to overfit

Deeper networks often generalize better. Each layer can focus on learning on a specific set of features.
They are good in learning non linear pattern in the data.

Deep networks without non-linearity behave like a single-layer network.

### Learning process in neural network
- forward pass: information move forward from the input to the output layer, going through the hidden intermediate layers.
We start with the input data, compute the activation score of each layer, and the calculates the error

-backpropagation: the error calculated before is propagated back to the network, and the weight of the nodes of the layer modified so that the error is decreased. This is how the network can learn.

## Sequence models
Sequential data example: time series, natural language.

Sequential data is data where order of the data is important. Therefore, for sequential data we should always keep in mind:
- order
- dimension (nr of data in the sequence)

The purpose of applying machine learning to sequential data is to be able to predict the next element in the sequence.

### Techiques used for predicting elements in sequence

- n-gram model: probabilistic model that is used to predict new values in sequences, based on the existing data. N is the number of previous elements in the sequence that has to be taken into account while doing the prediction. Many implementations uses N=3.
Drawback: this model takes into account only N previous elements, and therefore might lead to wrong predictions when the context is broader.

- bag of words: all words in the sequence in a bag. Every sequence can be represented with a fix size vector, whose elements are the count of each word in the sentence.
For example, if the dictionary contains the words "my", "name", "is", "ermanno"

|                     | my            | name | is | ermanno
| -------------       | ------------- |:----:| --:| -------:|
| my name is my name  | 2             | 2    |  1 |     0   |
| my name is paul     | 1             | 1    |  1 |     0   |  

- Sequence models: learn a representation of the past part of the sequence that carries information of past sequences with it.

### Applications of sequence models:
- Text classification
- Sentiment analysis
- Machine translation (i.e. machine language translation)
- Document summarization
- Caption generation

## Representation Learning for NLP
- one-hot encoding: given a Vocabulary V every word is converted to a vector of the length of V where 1 is put a the position of the word in the sentence we are analysing. It is easy to implement, but the meaning of the word is loss and there is a multiplication of the vectors for representing words.

i.e.
- V = ['I','am', 'Ermanno', 'name', 'is']

- Sentence: "I am Ermanno"
- Representation: I = [1,0,0,0,0];Am = [0,1,0,0,0], Ermanno = [0,0,1,0,0]

- Distributed Representations: capture semantics. The idea is to store meaning across a fixed number of dimentions (i.e. a matrix). The beauty of this approach is that you can train a neural network to do the categorization for you.

## Basic recurrent neural network (RNN)
Performs the same task for every element of the sequence, with the output being dependent on the previous task's computations.
It differs from other neural networks architecture because it uses the same parameters at every layer. Thus, it performs the same task at every step.

The hidden state is update with one input and has only one output. The input is in fact the output of previous calculations, and therefore we can say that the output for the node N depends on the outputs of all node N0..N-1.

RNNs are not good with sequences with big context.

## LSTM, GRU models
### LSTM: long short-term memory networks.
Recurrent network with the same structure of RNN, with a difference in the recurrent cell's structure. In particular, cells are allowed to keep or forget past outputs, and therefore the model learns by its own.
Three main feature of LSTM cells:
1. the memory cell captures long-term information
2. The working memory captures short-term information
3. Gates regulate the information exchange between the memory cell and working memory. They decide which information each cell has to keep and which one it has to discard (this is done by the forget gate with a forget regulator function)

Input gate: decides how much the current input matters based on it and on the previous state.

LSTM do not suffer of the gradient flow problem, as RRNs do.

### GRU: gated recurrent unit
Simplifies LSTM cells, by having only one update gate, one reset gate, one hidden state and no memory state.

The reset gate allows us to ignore the past states completely, while our update gate tells us to what extent we should update our hidden state at all.

In a sense, the update gate is the equivalent of both the input and forget gate in the LSTM cells.

## Vocabulary
- Loss: error made by the model on a single data point
- Cost function: avg of the loss function over the whole training data (overall error made by the model on the training data)
- Softmax: classifier often used in the last layer of the network. It converts final score into probabilities for each category.
- Gradient descent algorithm: algorithm used to make a neural network learn. It is a way to find the global minimum of the cost function (when the cost function is close to zero, the output of the neural network resemble the inputs, and therefore the net has learned to classify the data). It is called "gradient descent" because it calculates the gradient many times trying to "descend" to the global minimum of the cost function. The "learning rate", defines how "big" are the "steps" taken by the algorithm to reach the global minimum. The lower the more accurate is the gradient (but on the other hand the speed of the algorithm is lower).
- Mini-batch: random training inputs used when trying to minimize the cost function with the Stochastic gradient descent algorithm
- Stochastic gradient descent (SGD): optimisation technique that works on a random subset of the training set. Commonly used to optimise neural networks. In practice, it is a version of the gradient descent that uses "mini-batches" of data when trying to minimize the cost function. The gradient on the mini-batch data is calculated for the number necessary to complete an "epoch" of training.
- sigmoid function: function that maps values to a range of (0,1). Used as an activation "threshold" for neurons
- tanh function: function that map values between -1 and 1
- distributional hypothesis state: Words that appear in a similar context tend to be similar.
- vanishing gradient problem: The gradients with respect to the parameters in the layers far away from the output become extremely small
- vanishing gradient problem: the gradient tends to get smaller as we move backward through the hidden layers, that is, deeper layers tend to learn quicker than early layers. The more the network is deep, the more the problem is evident.
- exploding gradient problem: the gradient is getting bigger in early layers

## Q&A
- What is the proxy task solved by the Word2Vec model in its skip-gram variant? Predicting the context words based on the center word
- Which component of a long short-term memory (LSTM) cell facilitates the uninterrupted flow of the gradient? Memory cell
-

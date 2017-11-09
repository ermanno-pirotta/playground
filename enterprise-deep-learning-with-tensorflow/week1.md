# Enterprise deep learning with Tensorflow - week 1

TODO: VIDEO 2 AND 6

## What is deep learning?
Deep learning is an umbrella term for machine learning based on neural networks. It comprises a cascade of multiple layers of nonlinear processing units, where each output of layer N is the input of layer N+1.

Deep learning is an "end-to-end" learning approach, where features are learned by the model. In contrast with classical classification mechanisms, where features are given to the model, deep learning models incrementally build a representation of the features.

Neural networks: the signal is processed from the input, defined as the input layer,
through a set of layers called "hidden layers" to an output that's the result of the processing. Neural networks are really good in recognising patterns of data and they do not require explicit rules of features.

Classical classification models' pipeline:
- the features are extracted
- the model works on the features and classifies them

Deep learning models' pipeline:
- cascade of non linear transformation from the raw data to the classification.

Deep learning: multiple types of neural networks architectures:
- RNN / LSTM /GRU (used audio and video processing)
- Convolutional neural networks (CNN), used for image recognition
- Generative Adversarial Networks (GAN), they are analysing content but also generating new content that is similar to the one being learned

## How does a neural network learn?
The idea is that the network weights (the weights applied when transitioning from a node to another) are updated at each iteration to reduce the error of the network towards the target.
In particular, the network is given a set of input and outputs (so called "label") and at each run of the network the network's predictions are checked against the labels provided. The errors so calculated are then reflected backwards to the weight. Thi mechanism is called "backpropagation".

## TensorFlow main concepts
- Tensorflow: open source library for deep learning. It provides an abstraction layer so that you can run your algorithm everywhere. Supports python and other languages. It also provides canned estimators (built in algorithms for prediction).
- Tensor: n-dimentional matrix used to represent data.
- operations: TensorFlow operations, also called ops, are nodes that perform computations on or with the tensor objects.
- computation graph: type of directed graph that is used for defining the computational structure.
- sessions: responsible for graph execution
- Fetches: allow you to evaluate either a tensor or an operation object. When used on a tensor, it returns a NumPy array while when used on an operation object it returns "None"
- feed dictionary: allows you to override tensor objects in the graph.

## Why and when to use deep learning?
- classical machine learning models saturates with large data sets. In other words, after a certain point their performance decrease.
- in deep learning on other hands, the more data the better the results of the algorithm
- with a small dataset, deep learning performs is a similar way than classical machine learning algorithms.

Deep learning should be used when:
- you have a large amount of training data
- your problem is about image / audio recognition, or related to natural language processing
- the raw input has little structure and you need to learn meaningful information from them (i.e. pixels from an image). In other word, you need to learn features.

Deep learning should not be used when:
- you can solve the problem with a set of static rules
- you have a small amount of training data
- you have structured data
- you have features well separated


## Vocabulary
- Deep learning: subfield of artificial intelligence, machine learning and neural networks. It is ispired by how the human brain works, and it is the de-facto model behind artificial intelligence.
- Anaconda: Python package manager focused on scientific packages. It allows the creation of sandboxes.
- Jupyter notebook: Jupyter is a Web application that is used for live programming and visualizations. Jupyter allows writing code in Julia, Python, and R, but support other layers too.
- Tensorflow playground: lightweight neural network that run in a browser, and can be used for learning purposes.
- Keras is a simple, high-level neural networks library, written in Python that works as a wrapper to Tensorflow. Good for starting playing with machine learning.

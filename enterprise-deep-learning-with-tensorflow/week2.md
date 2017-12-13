# Enterprise deep learning with Tensorflow - week 2

## Machine learning development
Machine learning development model differs from standard development mode because:
- There is a lack of a clear vision of the outcome of the code you are writing.
- The outcome depends mostly on the data and the parameters with which you tune he algorithm
Therefore the process is a try and error process.

Machine learning development is an iterative process, which involves the following steps:
- collect the data
- build a model
- evaluate the model
- iterate and refine model based on results

### Data sets
When building a model, we use different set of data.
The sets are:
- Training set: used to optimise the model parameters to perform well on the training data.
- Development set: used to test the model on data it has never seen (called validation set or development set). It is also used to tune parameters of the model.
- Test set: presumably close to the production set.  The model tuned with the Development set on is then tested here. You do not tune model parameters on the test set.

There might be a mismatch between training set and Dev and test set, but never between Dev and test. There should not also be data alignment between the two.

How to split the data we have into training, Dev and test data? Keep the minimum for Dev and test that allow you to reflect the situation in production and leave the rest to training set.

### Errors to monitor
- On training set, model errors Vs human errors. If there is a big gap between the two, you have a bias problem and you should maybe increase the training set.
- Training Vs Dev errors: big gap = variance problem. You need to improve the generalisation through regularisation.
- Low training and low Dev errors, but big test error = you can try increase the test set since it might not be representative.

## Estimators

There are two groups of estimators:
- Canned estimators: prebuilt models.
- Custom estimators: you can define your own model. Support distributed training.

Estimators can be filled with data with input functions.

## Feature engineering
- Bucketing: converts numerical features into categorical ones based on when the numbers fall into a specific range
- Hashing: the features are categorized with an hashing function. Hashing produces a sparse representation, whereas embedding produces a dense representation.
With a hashed feature column, you do not need to provide a vocabulary list
- Embedding: vector that represents the meaning of a word. Embedding is learned automatically in the process of the neural network, and you should use embedding when you have a categorical column with a lot of values.
- Feature crosses: way of generating new feature based on existing ones. Useful with linear classifier
when different features are interlaced.



## Architectures for deep learning

In the machine learning field, the term architecture can have two different meanings:
- architecture of the deep model itself.
- software/hardware architecture with which you build the network

Types of networks:
- Input-output network
- Sequence models
- Convolutional networks (commonly used in computer vision)
- Shallow network: network with 0 or 1 hidden layer
- Deep network: network with more then 1 hidden layer

## Vocabulary

- Generalisation: ability of the neural network to perform well on unseen data.
- Underfit: the network is not powerful enough to represent the data. We say that the model has high bias.
- Overfit: the model is not general and represents all the data, including exceptional cases. The model has high variance. In general, one of the best ways of reducing overfitting is to increase the size of the training data.
- Regularisation: techniques to balance the model outcome so that it generalises well. They help reducing overfitting.
- [Facets](https://pair-code.github.io/facets/): open source tool for data visualisation.
- TensorFlow serving: library for serving system for machine learning models (to port to production your models).

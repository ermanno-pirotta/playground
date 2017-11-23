# Enterprise deep learning with Tensorflow - week 5

## Industry application of deep learning

- feasibility: is ML can solve the use case? 
- viability: is there a supporting use case from customers? Are customers willing to pay for the solution?
- desiderability: is the use case that can be solved by ML worth for customers?

### Feasibility
- check the data available for training (structured / unstructured data)
- is there human decision data available?
- are customers willing and allowed to share the data?
- can users make decisions only with the data they have? 

### Desiderability
- can ML algorithms reduce the number of people working on a solution?

Limitation of the ML algorithm approach:
- legal (i.e. explain to a judge why the algorithm make a certain decision)
- ethical
- acceptance

### Viability
- is the customer willing to pay for the ML algorithm?

Usually ML is bundled in the application, in order to be able to provide end-to-end solutions to customers.

## Applications in customer service
Problem: predict ticket categories (text classification )

Model selection:
- traditional methods (SVN with bag of words) do not keep word order and fail to capture similarity between words
- word2vec with neural network: capture similarity
- convolutional neural network with words: capture word order, but it has high memory demands when word Vocabulary is large. 
- convolutional neural network using characters: less memory is required (because the nr of characters to be saved in memory is less then the Vocabulary of words) and it is good in capturing word similarity. Moreover, since it is not bound to a fixed vocabulary of words, it can be used to categorise unseen words or spelling mistakes.

### How does a convolutional neural network using characters work?

Each character is encoded as an N-dimentional vector, and words are therefore matrix obtained by contatenating the characters vectors.

## Common evaluation metrics

- accuracy: ratio of correctly predicted observations to the total number of observations
- precision: ratio of correctly predicted positive observations to the total predicted positive observations.
- recall: ratio of correctly predicted positive observations to all observations in the actual category
- F1 score: harmonic mean of precision and recall which combines precision and recall

## Bank applications

- fraud detection
- credit scoring
- image processing
- recommender systems

## Medical applications

- medical image Segmentation

### Fully convolutional Networks

Neural networks that uses convolutional layers. The input is an image, and the output is an image.


## Vocabulary
- collaborative filtering models: collaborative filtering algorithms infer unspecified ratings by leveraging the user and items correlation of observed ratings.

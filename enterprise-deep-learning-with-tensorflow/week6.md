# Enterprise deep learning with Tensorflow - week 6

## Generative adversarial networks

Multiple networks and they are trained in an adversarial process (meaning that they oppose each other while trying to reach a common goal).

Images are created based on existing dataset, and they are similar to real images. This can be used to generate test data if those are missing.

GANs work better with continuous data.

### Generator network

- takes a vector of "noise" as input
- processes the noise vector with 2 dense hidden layer
- It is the final network used to generate new images

### Discriminator networks

They are able to tell whether or not generated images fit to the one in the training dataset (so that all generated images are "complaint" to what we want to achieve).
They do it as a binary classification task by solving this question: is the image from the training dataset or generated?

The Discriminator is the adversarial part of the generator network.

### Generation process

- Discriminator takes the first run, and gets several batches of images from the training dataset and from the generator. At this point, the generator only creates noisy images.
- The Discriminator then tries to tell which images are from the training set and which ones are from the Generator.
- Generator is learning from the Discriminator, not from the training set. The training is done via binary classification: the generated images are classified from the Discriminator into fake or real.

### GANs popular improvements

- Deep convolutional GANs (DC-GAN): use convolutional network instead of dense layers.
- Conditional GANs: supervised approach. Feed image labels as an additional input to both generator and Discriminator. It is a way to specify the "type" of image you are going to generate.

## Reinforcement learning

No initial dataset. The dataset is accumulated with experience by a machine learning agent that reacts to the environment.
How can you enable a reinforcement learning agent to learn about how the environment changes in time? Present a series of sequential frames from the environment as a single observation.

## Unsupervised learning

The dataset has no labels, but we have much larger dataset (millions of samples). This is coming to use particularly with IOT sensors' information.

Unsupervised learning is known to have the capacity to detect anomalies (i.e. financial fraud, iot power failures).

### Deep autoencoder network

Encodes from high dimentionality (input) to low dimentionality (bottleneck layer) and decode from low (bottleneck layer) to high dimentionality (output layer).

If the network is provided with a high number of example, it can use the difference between the input and the output, to detect anomalies.

The process works as follow:
- we split the data to consider particular sliding windows (the length of the timeframe depends on what we are analysing. I.e. years for financial historical data, minutes for industrial machine generated data)
- we provide the sliding windows as input, ad set a random weights of the network.
- every time we then provide a new sliding window, we take the it and compare with the previous output.
- after the network is trained, we take all examples as input and see when we have a high error

## Deep learning on mobile

The main problem is the "data movement" (read and set data), which is high battery consuming.

Optimisation approach:
- weights pruning: remove weights from the network. Usually it is done by training a large network, and then removing some weights. Usually this does not impact much the model accuracy.
- quantization: use fewer bits to represent data. Done when the network is already trained.

It is hard to put in place optimisation techniques, and therefore a number of tools can be used for that:
- TensorRT
- Tensorflow XLA
- Core ML
- NVML & UW

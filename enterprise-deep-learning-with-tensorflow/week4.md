# Enterprise deep learning with Tensorflow - week 4

## Convolutional networks (CNNs)
- biologically inspired
- object recognition involves combining many irregular features into a whole

One of the major problems with image recognition is spatial invariance, meaning that an image keeps its meaning even if elements are moved in the target space.

## How to cope with spatial invariance?
- Naive machine learning approaches: augment the data by generating many translations of the images in the training set, and then apply feed-forward neural network. This has performance problems, since it increases the size of the data set.
- CNNs apply the same transformation (and same parameters) to each patch of the image. In deeper layers of the network, the feature become more abstract and large. They are called convolutional neural network because they make use of convolution, a mathematical approach where a single output is calculated by applying the same transformation to matrix. i.e. take N pixels in an image, apply a weight function and maps it to a single pixels in a lower layer of the network.
Convolution is repeated from layer to layer, and in this way the deeper layers are "learning" more complex representation of the image you are trying to recognise. (this means that after each layer the effective field of view within the input image is increased).

Weights in the CNN are randomly initialised, and are shared by the output pixels.

There are multiple possible "kernel shapes" used in convolution:
- 3x3 (3 by 3 matrix mapped to 1)
- 1-1 (1 by 1 matrix mapped to 1)
- 1x3 (1 by 3 matrix mapped to 1)

## Pooling
A standard CNN network structure is the following:
INPUT - [convolution][ReLU] - [convolution][ReLU] .. - [convolution][ReLU] - [classification (with Softmax)] - OUTPUT

This structure is not very performant, because each convolution operates with the same "resolution" (aka kernel shape). In other words, after you decide the kernel shape each convolution operations work on the same structure.
It may be desirable to decrease the complexity while moving to deeper layers of the network, by downsampling (Pooling).

After each pooling, double the number of channels. Thus, the networks architecture becomes:

INPUT - [convolution][ReLU] - [convolution][ReLU] .. - [convolution][ReLU][Pooling]- [increase nr of channels][convolution][ReLU] .. - [classification (with Softmax)] -OUTPUT

### How does pooling works:

- Average pooling: consider a matrix (i.e 2x2) of features, and then make the average of the values
- Max pooling: consider a matrix (i.e 2x2) of features, and then take the maximum of the values

Common pooling methods (screenshot from course slides):
![Pooling methods]("https://github.com/ermanno-pirotta/playground/raw/master/enterprise-deep-learning-with-tensorflow/pooling.png")

- Global average pooling: take all features (i.e. pixels), make the average and produce a 1 by 1 matrix. This is done for each channel. i.e. 6 channels with 4x4 layer -> 6 outputs

## Advantages of CNN:
- fewer parameters to tune
- spatial invariance built in mathematically
- retains spatial relationship between features
- the features are "learned" by the network, and therefore there is not need for feature engineering as in other approaches.

## Dense layer
Every pixel in all output channels is connected to every neuron in the layer (-> the weight matrix becomes very large!). To avoid the dimentional problems of dense layers, it is bettert to first use global pooling and then connect it to every neuron of the network.
Dense layers architectures, having a lot of parameters, tend to overfit.

## Drop out layer
Its purpose is to mitigate overfitting in neural networks.
It takes an input layer and randomly set a % of its neurons to 0. By doing this at every layer, it forces the neural network to generalize. We can also apply dropout to convolution layers.

- spatial dropout: instead of dropping out specific pixels, drop out a set of pixels

## Accelerating Deep CCN training
- reduce the filter view. I.e. replace a 7x7 filter view by 2 layers with a two subsequent 3x3 views. This reduce overfitting, and increase non-linearity (because between each layer there is a non-linearity function)
- reduce the number of dense layers (fully connected layers)
- batch normalization

### batch normalization
normalize all the output distribution of each layer.
Benefits:
- higher accuracy in earlier training step
- reduction of overfitting
- less need of dropout layers

## Transfer learning
Recycle a predefined model. This overcome the time that is required to train a neural network by using the result of what have been learned in other networks (i.e. googlenet).

In particular, lower layers of convolution network keep local features (i.e. lines) and therefore can be reused.

We can have 2 scenario of Transfer learning:
- we keep initial levels and change only the last classification levels
- we keep only a small set of levels, and customize the rest (requires more training data and training effort)

## Deep residual networks
Problem: deep network are more expressive, but they are hard to train

Solution: the modeled function of each building block has a higher resemblance to the identity function (than the zero function).

Deep residual networks allow network architectures with 100 layers.


## object detection
### Naive approach

Create a sliding window" (chunk of the image that slides through the entire image), and apply a classification to the slide. When the probability of the slide for a given chunk is high, it means that the slide corresponds to the object we want to detect.

### two stage detector (RCNN)

Instead of having a "sliding window" (chunk of the image that slides through the entire image), we use a proposal algorithm (selective search) that propose "chunks" of the images based on texture, color, intensity. The classification is then applied only to the selected slides, therefore speeding up the computation.
The structure of the network is the following:

INPUT- [proposal algorithm][CNN][[SVM(for object classification)][Linear regression (for improving bounding boxes)]] - OUTPUT

Problems with this setup:
- the CNN cannot adapt to changes in SVM and linear regression, because the models are trained separately
- bounding boxes usually have large overlaps, and therefore most of the CNN work is "wasted" on this

### Fast RCNN
Improves RCNN by:
- replacing SVM by softmax
- combining regression and classification in one neural network
- evaluating CNN only one time / image

The network architecture is therefore:

INPUT- [CNN][FEATURE MAP][NEURAL NETWORK] [[softmax] [linear regression]] - OUTPUT

The main performance problem in this architecture is the proposal algorithm. As a solution add a "Region Proposal network".

### One stage detector

INPUT - [CNN (encoder)] - [UPSAMPLE (decoder)] - OUTPUT

To put it simple, it removes the proposal algorithm. It is faster, simpler to design but less accurate.

Predicts jointly in one pass bounding boxes and class probabilities.

## Image segmentation

i.e. classify background vs a target object. U-Net architecture is an example of this.

Generic architecture:

INPUT - [CNN (encoder)] - [UPSAMPLE [decoder]] - OUTPUT


## Vocabulary
- estimator API: allows to easily wrap Tensorflow calls
- transpose convolution = deconvolution = aka strided convolution
- ReLU: Non-linearity activation function, introduced in the middle of each convolution from layer to layer. In Tensorflow, the activation function is "embedded" in the convolutional layer. ReLus do not saturate.
- softmax classification layer: layer that output a probability for each neuron to match a specific feature.
- field of view: dimension of the matrix used for convolution
- general convolution output: A weighted sum of the inputs

## Q&A (from weekly exam)

- Why is it problematic to augment data with a spatial invariant set in a feed forward network? Because the training time and the complexity of the model are increased due to the increasing data size
- Which pattern is typically used to design convolutional neural networks? The number of channels increases while the resolution decreases
- How can you obtain the predicted class label from the output neurons of a convolutional neural network (the logits)? Select the index of the neuron with the largest value
- Which of the following models belong to the one-stage detector family? YOLO (9000), RetinaNet, and SSD

const vision = require("@google-cloud/vision");
const path = require("path");

const client = new vision.ImageAnnotatorClient({
  keyFilename: path.join(__dirname, "private-key.json"),
});

async function analyzeImage(imagePath) {
  const [result] = await client.annotateImage({
    image: { source: { filename: imagePath } },
    features: [
      { type: "LABEL_DETECTION" },
      { type: "FACE_DETECTION" },
      { type: "TEXT_DETECTION" },
    ],
  });

  const labels = (result.labelAnnotations || []).map((l) => l.description);
  const faces = (result.faceAnnotations || []).map(analyzeFace);
  const text = (result.textAnnotations || []).map((t) => t.description);
  console.log(text);

  // concat into one array
  const cleaned = [text[0], labels[0], faces[0]];
  return cleaned;
}

function analyzeFace(face) {
  // Get which emotion is highest confidence
  const emotions = [
    "joyLikelihood",
    "sorrowLikelihood",
    "angerLikelihood",
    "surpriseLikelihood",
  ];
  let highestEmotion = null;
  let highestConfidence = -1;
  emotions.forEach((emotion) => {
    const confidence = face[emotion];
    if (confidence > highestConfidence) {
      highestConfidence = confidence;
      highestEmotion = emotion.replace("Likelihood", "");
    }
  });
  return highestEmotion;
}

module.exports = { analyzeImage };

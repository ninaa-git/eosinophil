import qupath.lib.io.GsonTools
import qupath.lib.images.servers.LabeledImageServer

def project = getProject()
for (entry in project.getImageList()) {
    def imageData = entry.readImageData()
    def hierarchy = entry.readHierarchy()
    def annotations = hierarchy.getAnnotationObjects()
    //def name = entry.getImageName()
    print(entry)
    print(imageData)
    print(hierarchy)
    print(annotations)



    def OUTPUT_DIR = 'Your Directory' 
    def name = GeneralTools.getNameWithoutExtension(imageData.getServer().getMetadata().getName())
    print(name)
    def filePath = buildFilePath(OUTPUT_DIR, name.toString())
    print(filePath)
    mkdirs(filePath)
    boolean prettyPrint = true
    def gson = GsonTools.getInstance(prettyPrint)
    def writer = new FileWriter(filePath + ".json"); //or FileWriter(filePath) on Windows
    gson.toJson(annotations,writer)

    exportObjectsToGeoJson(annotations, filePath+ ".geojson") // ou exportObjectsToGeoJson(annotations, OUTPUT_DIR, "a.geojson")
    
    writer.flush()
    print("done")
}

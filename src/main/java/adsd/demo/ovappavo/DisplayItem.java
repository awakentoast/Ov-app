package adsd.demo.ovappavo;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DisplayItem {
    String displayString;
    List<Image> iconsStart;
    List<Image> iconsEnd;
    String[] iconPaths = {"src/main/java/images/OVapp/rampIcon.png",
            "src/main/java/images/OVapp/toiletIcon.jpeg",
            "src/main/java/images/OVapp/serviceEmployeeIcon.png",
            "src/main/java/images/OVapp/AEDIcon.jpeg"};

    public DisplayItem(String displayString, boolean[] iconsStart, boolean[] iconsEnd) {
        this.displayString = displayString;
        this.iconsStart = getIcons(iconsStart);
        this.iconsEnd = getIcons(iconsEnd);
    }

    private List<Image> getIcons(boolean[] services) {
        List<Image> icons = new ArrayList<>();
        int index = -1;
        for (boolean service : services) {
            index++;
            if (service) {
                icons.add(new Image("file:" + iconPaths[index], 32,32, true, true));
            }
        }
        return icons;
    }

    public String getDisplayString() {
        return displayString;
    }
}


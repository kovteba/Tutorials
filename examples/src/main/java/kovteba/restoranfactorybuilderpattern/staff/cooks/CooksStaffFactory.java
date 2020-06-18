package kovteba.restoranfactorybuilderpattern.staff.cooks;

public class CooksStaffFactory {

    public InitCooks setupCook(CooksLIst type) {
        switch (type) {
            case COOK_HOT_POSITION:
                return new CookHotPosition();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}

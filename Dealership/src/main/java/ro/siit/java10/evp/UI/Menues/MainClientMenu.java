package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.*;
import ro.siit.java10.evp.VehicleSorter.FilterOptions;
import ro.siit.java10.evp.VehicleSorter.SortingOptions;

import java.util.ArrayList;
import java.util.List;

public class MainClientMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.MAIN_MENU;

    protected MainClientMenu() {

        super(THIS_MENU_TYPE);
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        int option;

        option = doSelection();

        switch (option){
            case (0):
                printAllDealershipsStockVehicleList();
            break;
            case (1):
                MenuTypes.PICK_DEALERSHIP.setMenu(new PickDealershipMenu(THIS_MENU_TYPE));
                return MenuTypes.PICK_DEALERSHIP;
            case (2):
                MenuTypes.MY_ACCOUNT.setMenu(new MyAccountMenu(THIS_MENU_TYPE));
                return MenuTypes.MY_ACCOUNT;
        }

        return THIS_MENU_TYPE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Print complete list of vehicles in stock");
        selections.add("Select dealership");
        selections.add("My Account");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

    private void printAllDealershipsStockVehicleList(){

        List<Dealership> dealershipList = dCentral.getDealershipList();

        for (Dealership instance : dealershipList){

            consIO.printString(instance.getName() + "\n");

            List<VehicleData> vehicleList = instance.getVehicleSorter().getVehicleList(SortingOptions.NORMAL,
                    FilterOptions.STOCK);

            for(VehicleData vd: vehicleList){
                consIO.printString(vd.stringRepresentation() + "\n");
            }
        }
    }
}

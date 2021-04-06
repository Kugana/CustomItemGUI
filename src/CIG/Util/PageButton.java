package CIG.Util;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.function.Consumer;



public class PageButton {

    private static int counter;
    private final int ID = counter++;

    private ItemStack itemStack;
    private Consumer<InventoryClickEvent> action;


    @SuppressWarnings("unused")
    public PageButton(ItemStack itemStack) {
        this(itemStack, event -> {
        });
    }


    public PageButton(ItemStack itemStack, Consumer<InventoryClickEvent> action) {
        this.itemStack = itemStack;
        this.action = action;
    }




    @SuppressWarnings("WeakerAccess")
    public ItemStack getItemStack() {
        return itemStack;
    }


    @SuppressWarnings("unused")
    public void setAction(Consumer<InventoryClickEvent> action) {
        this.action = action;
    }


    @SuppressWarnings("WeakerAccess")
    public void onClick(InventoryClickEvent event) {
        action.accept(event);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PageButton)) {
            return false;
        }
        PageButton button = (PageButton) o;
        return ID == button.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}


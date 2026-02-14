package org.betterx.wover.item.api;

import net.minecraft.resources.Identifier;
import org.betterx.wover.tag.api.event.context.ItemTagBootstrapContext;

/**
 * If an Item Object implements this interface and is registered with the ItemRegistry, #registerItemTags will be called
 * during the tag bootstrap datagen process.
 */
//TODO: @Deprecated(forRemoval = true, since = "21.6.0")
public interface ItemTagProvider {
    /**
     * Register item tags for the given location.
     *
     * @param location The location of the item as it was registered with then ItemRegistry
     * @param context  The context to add tags to
     */
    void registerItemTags(Identifier location, ItemTagBootstrapContext context);
}

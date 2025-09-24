package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkElement;

import java.util.Collection;

/**
 * Provides access to RebrkElement objects.
 * Implementations of this interface allow fetching elements by their number
 * and retrieving all elements associated with a specific minifigure ID.
 */
public interface RebrkElementClient {

    /**
     * Fetches a RebrkElement by its unique number.
     *
     * @param number the unique number of the element
     * @return the RebrkElement corresponding to the provided number
     */
    RebrkElement getElement(String number);

    /**
     * Retrieves all RebrkElement objects associated with a specific minifigure ID.
     *
     * @param id the unique identifier of the minifigure
     * @return a collection of RebrkElement objects associated with the specified minifigure ID
     */
    Collection<RebrkElement> getElementsFromMinifig(String id);
}

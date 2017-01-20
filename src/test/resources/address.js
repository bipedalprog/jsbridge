/**
 * Determine if the address conforms to our validation rules.
 */
function isValidShippingAddress(address) {
    // Only US addresses are supported now.
    if (address.country && address.country === "USA") {
        // We cannot shipt to a PO Box.
        if (address.poBox) return false;
        // We need a locality.
        if (!address.zipCode || !address.state || !address.city) return false;
        // And a street address.
        if (!address.street1) return false;
        return true;
    } else {
        return false;
    }
}

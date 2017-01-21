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

/**
 * Multiple argument function.
 */
function makeAddress(country, zip, state, city, address1, address2) {
    var me = {};
    me.country = country;
    me.zip = zip;
    me.state = state;
    me.city = city;
    me.address1 = address1;
    me.address2 = address2;
    return me;
}
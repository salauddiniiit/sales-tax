# Introduction #

The solution of this problem was modeled as a simplified e-commerce architecture to support the following requirements:
  * Purchase goods of different type
  * Apply the correct tax amounts bases on the type of product
  * Provide a receipt showing details of the purchased items

# Details #

The solution had the following goals:
  * Provide support for goods of different nature
  * Ability to calculate the correct tax amount based of good types
  * Provide a receipt showing details of the purchased goods
  * Provide a basic architecture that provide flexibility to be easily extendable
  * Develop the solution using Test Driven Development

The implementation is following a Apache maven code structure.

# Objects #

**Good**
this object is the basic container that describes the nature of the goods like name, base price, source, tax assignment.

**TaxCode**
this object describes a unique tax identifier that is use to correlate goods with tax brackets

**Taxes**
this object is used to assign a specific tax value to a tax code

**TaxStrategy**
this object has a group of taxes that can be applied to goods and the business logic to apply them

**TaxStrategyFactory**
this factory is used to provide the ability to have multiple tax strategies based on specific parameters (ie. country)

**ShoppingItem**
this object contains details about the good being purchased as well as to provide a snapshot of costs associated with it

**ShoppingCart**
the goal of this object provide the means to manipulate the items being purchased and to delegate the calculation of the goods cost to different strategies
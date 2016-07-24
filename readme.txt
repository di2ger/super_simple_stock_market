This is the implementation of assignment "Super Simple Stock Market"

It's been decided to use Java 8, Java Spring as IoC and Maven as dependency manager and project builder.

Stocks have been decided to organize as a tree of inherited classes in order to reduce code duplications and assuming arriving of new stock subtypes.

It is not clear what stocks should we consider different, it's been decided to use all fields except Last Dividend.

Formula for P/E Ratio is unclear - Dividend has not been defined, so it's been decided to use Dividend Yield formula as analogy.

Calculate Volume Weighted Stock Price - it's been decided that all provided trades could be for different stocks, so there is a need to filter trades.

Engine holds general calculators which allows configuration of multiple implementations of the same calculation types.
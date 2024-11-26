# inventory-management-system
Building an inventory management system in Java-requirements
1. Adding a new product.
2. Viewing products in the inventory.
3. Updating product details (like price or quantity).
4. Removing a product from inventory.
5. Searching for products by name, category, or ID.
6. Generating reports (e.g., low stock alerts).

package structure
com.inventory
    ├── model        # Data models (Product class, etc.)
    ├── dao          # Data Access Object (DAO) layer for database interaction
    ├── service      # Business logic layer
    ├── ui           # User Interface layer (console-based, Swing, or JavaFX)
    └── util         # Utility classes (database connection, etc.)


interface NavAttributes {
  [propName: string]: any;
}
interface NavWrapper {
  attributes: NavAttributes;
  element: string;
}
interface NavBadge {
  text: string;
  variant: string;
}
interface NavLabel {
  class?: string;
  variant: string;
}

export interface NavData {
  name?: string;
  url?: string;
  icon?: string;
  badge?: NavBadge;
  title?: boolean;
  children?: NavData[];
  variant?: string;
  attributes?: NavAttributes;
  divider?: boolean;
  class?: string;
  label?: NavLabel;
  wrapper?: NavWrapper;
}

export const navItems: NavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer',
    badge: {
      variant: 'info',
      text: 'NEW'
    }
  },
  {
    title: true,
    name: 'Theme'
  },
  {
    name: 'Settings',
    url: '/api',
    icon: 'icon-star',
    children: [
      {
        name: 'Tax Master',
        url: '/taxes',
        icon: 'icon-star'
      },
      {
        name: 'Category',
        url: '/category',
        icon: 'icon-star'
      },
      {
        name: 'Sub Category',
        url: '/subcategory',
        icon: 'icon-star'
      },
      {
        name: 'Uom',
        url: '/uom',
        icon: 'icon-star'
      },
      {
        name: 'Customer',
        url: '/customer',
        icon: 'icon-star'
      },
      {
        name: 'Supplier',
        url: '/supplier',
        icon: 'icon-star'
      }
    ]
  },

  {
    name: 'Products',
    url: '/product',
    icon: 'icon-star',
    children: [
      {
        name: 'Product List',
        url: '/product/product-list',
        icon: 'icon-star',
      }
      ]
    },
    {
      name: 'Accounts',
      url: '/accounts',
      icon: 'icon-star',
      children: [
        {
          name: 'Account Group',
          url: '/accounts/accountgroup-list',
          icon: 'icon-star',
        },
        {
          name: 'Chart Of Account',
          url: '/accounts/account-list',
          icon: 'icon-star',
        }
        ]
      },
      {
        name: 'Invoices',
        url: '/invoice',
        icon: 'icon-star',
        children: [
          {
            name: 'Invoice List',
            url: '/invoice/invoice-list',
          },
          {
            name: 'Create New Invoice',
            url: '/invoice/new-invoice',
          }
          ]
        },
        {
          name: 'Purchase',
          url: '/purchase',
          icon: 'icon-star',
          children: [
            {
              name: 'Purchase List',
              url: '/purchase/purchase-list',
            },
            {
              name: 'Create New Purchase',
              url: '/purchase/new-purchase',
            }
            ]
          },
          {
          name: 'Invoice Report',
          url: '/invoicereport',
          icon: 'icon-star',
          children: [
            {
              name: 'Invoice Sales History Report',
              url: '/invoicereport/invoice-sales-history',
            },
            {
              name: 'Item Wise Sales History Report',
              url: '/invoicereport/item-wise-sales-history',
            },
            {
              name: 'Customer Wise Sale Report',
              url: '/invoicereport/customer-wise-sale-history',
            }
            ]
          },
          {
            name: 'Purchase Report',
            url: '/purchasereport',
            icon: 'icon-star',
            children: [
              {
                name: 'Purchase History Report',
                url: '/purchasereport/purchase-history',
              }
              ]
            },
];

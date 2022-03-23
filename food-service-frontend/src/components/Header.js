import { Fragment, useEffect, useState } from "react";
import { Popover, Transition } from "@headlessui/react";
import {
    MenuIcon
  } from '@heroicons/react/outline';

import { ChevronDownIcon } from "@heroicons/react/outline";

import ProductService from "../api/ProductService";


const Header = () => {

    const [categoryList, setCategoryList] = useState([]);
    const [message, setMessage] = useState("Loading food categories");

    const productService = new ProductService();


    useEffect( () => {
        async function fetchCategories() {
            console.log("Fetching the categories list");

            const res = await productService.fetchCategoryList();

            if (res && res.success) {
                setCategoryList(res.data);
            } else {
                console.log("Failed to retreive category data\n"+res.data);
                setMessage(res.error);    
            }
        }
        fetchCategories();
    }, []);


    function classNames(...classes) {
        return classes.filter(Boolean).join(' ')
    }


    return (
        <Popover className="relative bg-white">
            <div className="max-w-auto mx-auto px-4 sm:px-6">
                <div className="flex justify-between items-left border-b-2 border-gray-100 py-6 md:justify-start md:space-x-10">
                
                <div className="flex justify-start lg:w-0 lg:flex-1">
                    <a href="/">
                    <h1 className="text-lg font-bold mb-2.5 text-teal-700">Food Corner</h1>
                    </a>
                </div>

                <div className="-mr-2 -my-2 md:hidden">
                    <Popover.Button className="bg-white rounded-md p-2 inline-flex items-left justify-left text-gray-400 hover:text-gray-500 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-indigo-500">
                    <span className="sr-only">Open menu</span>
                    <MenuIcon className="h-6 w-6" aria-hidden="true" />
                    </Popover.Button>
                </div>
                
                <Popover.Group as="nav" className="hidden md:flex space-x-10">
                    <Popover className="relative">
                    {({ open }) => (
                        <>
                        <Popover.Button
                            className={classNames(
                            open ? 'text-gray-900' : 'text-gray-500',
                            'group bg-white rounded-md inline-flex items-left text-base font-medium hover:text-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500'
                            )}
                        >
                            <span>Categories</span>
                            <ChevronDownIcon
                            className={classNames(
                                open ? 'text-gray-600' : 'text-gray-400',
                                'ml-2 h-5 w-5 group-hover:text-gray-500'
                            )}
                            aria-hidden="true"
                            />
                        </Popover.Button>

                            <Popover.Panel className="absolute z-10 -ml-4 mt-3 transform px-2 w-screen max-w-md sm:px-0 lg:ml-0 lg:left-1/2 lg:-translate-x-1/2">
                            <div className="rounded-lg shadow-lg ring-1 ring-black ring-opacity-5 overflow-hidden">
                                <div className="relative grid gap-6 bg-white px-5 py-6 sm:gap-8 sm:p-8">
                                {categoryList.map((item) => (
                                    <a
                                    key={item.name}
                                    href={'/category/'+item.id}
                                    className="-m-3 p-3 flex items-start rounded-lg hover:bg-gray-50"
                                    >
                                    <div className="ml-4">
                                        <p className="text-base font-medium text-gray-900">{item.name}</p>
                                        {/* <p className="mt-1 text-sm text-gray-500">{item.description}</p> */}
                                    </div>
                                    </a>
                                ))}
                                
                                </div>
                            </div>
                            </Popover.Panel>
                        </>
                    )}
                    </Popover>
                    <a href="#" className="text-base font-medium text-gray-500 hover:text-gray-900">
                    Search
                    </a>
                    <div class="flex justify-center">
                    <div class="mb-3 xl:w-96">
                        <div class="input-group relative flex flex-wrap items-stretch w-full mb-4">
                        <input type="search" class="form-control relative flex-auto min-w-0 block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-green-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-emerald-600 focus:outline-none" placeholder="Food items" aria-label="Search" aria-describedby="button-addon3"/>
                        </div>
                    </div>
                    </div>

                    
                </Popover.Group>
                <div className="hidden md:flex items-left justify-end md:flex-1 lg:w-0">
                    
                    <a
                    href="#"
                    className="ml-8 whitespace-nowrap inline-flex items-left justify-left px-4 py-2 border border-transparent rounded-md shadow-sm text-base font-medium text-white bg-green-600 hover:bg-green-700"
                    >
                    Carts
                    </a>
                </div>
                </div>
            </div>

            <Transition
                as={Fragment}
                enter="duration-200 ease-out"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="duration-100 ease-in"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
            >
            </Transition>
        </Popover>        
    );

}

export default Header;
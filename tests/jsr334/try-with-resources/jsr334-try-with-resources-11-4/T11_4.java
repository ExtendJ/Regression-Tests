
class T11_4 {
    
	class ResourceInitializationException extends Exception {}
	class NonInitializableResource implements AutoCloseable {
		public NonInitializableResource() throws ResourceInitializationException {
			throw new ResourceInitializationException();
		}
		public void close() {}
	}

	public void foo() {
		try {
			try (NonInitializableResource r = new NonInitializableResource()) {
			}
		} catch (ResourceInitializationException e) {
			return;
		}
	}

}